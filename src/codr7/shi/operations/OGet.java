package codr7.shi.operations;

import codr7.shi.ICell;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Cells;

public final class OGet implements Operation {
    private final int rSource;

    public OGet(final int rSource) {
        this.rSource = rSource;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            stack.push(registers[rSource]);
            return pc + 1;
        };
    }
}
