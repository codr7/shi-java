package codr7.shi.operations;

import codr7.shi.IValue;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Cells;

public final class OReturn implements Operation {
    public OReturn() {
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final IValue[] registers) -> vm.popCall().returnPc();
    }
}
