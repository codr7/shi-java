package codr7.shi.operations;

import codr7.shi.ICell;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Cells;

public final class PushValue implements Operation {
    private final ICell value;

    public PushValue(final ICell value) {
        this.value = value;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            stack.push(value);
            return pc + 1;
        };
    }
}
