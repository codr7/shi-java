package codr7.shi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VM {
    public final ArrayList<IValue> registers = new ArrayList<>();
    private final List<Op> ops = new ArrayList<>();
    private Op.Eval[] opEvals = {};

    public int alloc(final int n) {
        final var result = registers.size();

        for (var i = 0; i < n; i++) {
            registers.add(null);
        }

        return result;
    }

    public void compile(final int fromPc) {
        opEvals = Arrays.copyOf(opEvals, ops.size());

        for (var pc = fromPc; pc < ops.size(); pc++) {
            opEvals[pc] = ops.get(pc).compile(this, pc);
        }
    }

    public void emit(final Op op) {
        ops.add(op);
    }

    public int emitPc() {
        return ops.size();
    }

    public void eval(final int fromPc, int toPc, final Stack stack) {
        if (toPc == -1) {
            toPc = ops.size() - 1;
        }

        if (opEvals.length < ops.size()) {
            compile(opEvals.length);
        }

        for (var pc = fromPc; pc <= toPc; pc = opEvals[pc].eval(stack)) {
            // Do nothing
        }
    }
}
