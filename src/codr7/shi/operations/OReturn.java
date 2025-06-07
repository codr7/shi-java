package codr7.shi.operations;

import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public final class OReturn implements Operation {
    public OReturn() {
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Values stack) -> vm.popCall().returnPc();
    }
}
