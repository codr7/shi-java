package codr7.shi;

import codr7.shi.readers.RForm;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VM {
    private final Reader reader = RForm.INSTANCE;
    private final List<Operation> operations = new ArrayList<>();
    private final Library userLibrary = new Library("user", null);
    private IValue[] registers = {};
    private int registerCount = 0;
    private Library currentLibrary = userLibrary;
    private Call callStack = null;
    private Operation.Evaluate[] code = {};
    private Path path = Paths.get("");

    public int allocate(final int count) {
        final var result = registerCount;
        registerCount += count;
        return result;
    }

    public void compile(final int fromPc) {
        code = Arrays.copyOf(code, operations.size());

        for (var pc = fromPc; pc < operations.size(); pc++) {
            code[pc] = operations.get(pc).compile(this, pc);
        }
    }

    public Library currentLibrary() {
        return currentLibrary;
    }

    public void emit(final Operation operation) {
        operations.add(operation);
    }

    public int emitPc() {
        return operations.size();
    }

    public void evaluate(final int fromPc, int toPc, final Cells stack) {
        if (toPc == -1) {
            toPc = operations.size();
        }

        if (code.length < operations.size()) {
            compile(code.length);
        }

        if (registers.length < registerCount) {
            registers = Arrays.copyOf(registers, registerCount);
        }

        for (var pc = fromPc; pc < toPc; pc = code[pc].eval(stack, registers)) {
            // Do nothing
        }
    }

    public void evaluate(final String in, final Cells stack, final Sloc sloc) {
        final var startPc = emitPc();
        final var forms = new Forms();
        read(new Input(new StringReader(in), sloc), forms);
        forms.emit(this);
        evaluate(startPc, -1, stack);
    }

    public void load(final Path path) {
        final var prevPath = this.path;
        final var p = prevPath.resolve(path);
        final var sloc = new Sloc(p.toString());
        this.path = p.getParent();

        try {
            final var forms = new Forms();

            try {
                read(new Input(Files.newBufferedReader(p), sloc), forms);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }

            forms.emit(this);
        } finally {
            this.path = prevPath;
        }
    }

    public Call popCall() {
        final var result = callStack;
        callStack = result.parent();

        System.arraycopy(
                result.argumentRegisters(), 0,
                registers, result.target().rArguments,
                result.target().arguments.length);

        return result;
    }

    public void pushCall(ShiMethod target, Sloc sloc, int returnPc) {
        final var callRegisters = Arrays.copyOfRange(
                registers,
                target.rArguments,
                target.rArguments + target.arguments.length);

        callStack = new Call(callStack, target, sloc, callRegisters, returnPc);
    }

    public void read(final Input in, final Forms out) {
        while (readForm(in, out)) {
            // Do nothing
        }
    }

    public boolean readForm(final Input in, final Forms out) {
        return reader.read(this, in, out);
    }

    public void withLibrary(Library library, final Callback callback) {
        if (library == null) {
            library = new Library(currentLibrary.name, currentLibrary);
        }

        final var prev = currentLibrary;
        currentLibrary = library;

        try {
            callback.call();
        } finally {
            currentLibrary = prev;
        }
    }

    public interface Callback {
        void call();
    }
}
