package codr7.shi.operations;

import codr7.shi.Cells;
import codr7.shi.ICell;
import codr7.shi.Operation;
import codr7.shi.VM;

public final class SetRegister implements Operation {
    private final int rTarget;
    private final int count;

    public SetRegister(final int rTarget, final int count) {
        this.rTarget = rTarget;
        this.count = count;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            for (var i = 0; i < count; i++) {
                registers[rTarget + i] = stack.pop();
            }

            return pc + 1;
        };
    }
}
