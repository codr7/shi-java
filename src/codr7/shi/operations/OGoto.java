package codr7.shi.operations;

import codr7.shi.*;

public final class OGoto implements Operation {
    private final Label target;

    public OGoto(final Label target) {
        this.target = target;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final IValue[] registers) -> target.pc;
    }
}
