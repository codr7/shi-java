package codr7.shi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VM {
    public final ArrayList<IValue> registers = new ArrayList<>();
    private final List<Operation> operations = new ArrayList<>();
    private Operation.Eval[] code = {};
    private final Library userLibrary = new Library(Symbol.get("user"), null);
    private Library currentLibrary = userLibrary;

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

    public void eval(final int fromPc, int toPc, final Values stack) {
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
}
