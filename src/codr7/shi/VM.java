package codr7.shi;

import codr7.shi.readers.RForm;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VM {
    public final ArrayList<IValue> registers = new ArrayList<>();
    private final Reader reader = RForm.INSTANCE;
    private final List<Operation> operations = new ArrayList<>();
    private final Library userLibrary = new Library("user", null);
    private Library currentLibrary = userLibrary;
    private Call callStack = null;
    private Operation.Evaluate[] code = {};

    public int allocate(final int n) {
        final var result = registers.size();

        for (var i = 0; i < n; i++) {
            registers.add(null);
        }

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

    public void evaluate(final int fromPc, int toPc, final Values stack) {
        if (toPc == -1) {
            toPc = operations.size() - 1;
        }

        if (code.length < operations.size()) {
            compile(code.length);
        }

        for (var pc = fromPc; pc <= toPc; pc = code[pc].eval(stack)) {
            // Do nothing
        }
    }

    public void evaluate(final String in, final Values stack, final Sloc sloc) {
        final var startPc = emitPc();
        final var forms = new Forms();
        read(new Input(new StringReader(in), sloc), forms);
        forms.emit(this);
        evaluate(startPc, -1, stack);
    }

    public Call popCall() {
        final var result = callStack;
        callStack = result.parent();
        return result;
    }

    public void pushCall(Sloc sloc, Method target, int returnPc) {
        callStack = new Call(callStack, sloc, target, returnPc);
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
