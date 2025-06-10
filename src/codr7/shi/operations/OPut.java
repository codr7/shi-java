package codr7.shi.operations;

import codr7.shi.IValue;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Cells;

public final class OPut implements Operation {
    private final int rTarget;
    private final int count;

    public OPut(final int rTarget, final int count) {
        this.rTarget = rTarget;
        this.count = count;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final IValue[] registers) -> {
            for (var i = 0; i < count; i++) {
                registers[rTarget + i] = stack.pop();
            }

            return pc + 1;
        };
    }
}
