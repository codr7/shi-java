package codr7.shi.operations;

import codr7.shi.ICell;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Cells;

public final class Return implements Operation {
    public Return() {
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> vm.popCall().returnPc();
    }
}
