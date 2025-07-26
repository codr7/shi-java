package codr7.shi.operations;

import codr7.shi.Cells;
import codr7.shi.ICell;
import codr7.shi.Operation;
import codr7.shi.VM;

public final class GetRegister implements Operation {
    private final int rSource;

    public GetRegister(final int rSource) {
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
