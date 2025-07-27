package codr7.shi.operations;

import codr7.shi.Cells;
import codr7.shi.ICell;
import codr7.shi.Operation;
import codr7.shi.VM;

public final class GetRegister implements Operation {
    private final int rSource;
    private final int count;

    public GetRegister(final int rSource, final int count) {
        this.rSource = rSource;
        this.count = count;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            for (var i = 0; i < count; i++) {
                stack.push(registers[rSource + i]);
            }

            return pc + 1;
        };
    }
}
