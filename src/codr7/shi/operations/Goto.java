package codr7.shi.operations;

import codr7.shi.*;

public final class Goto implements Operation {
    private final Label target;

    public Goto(final Label target) {
        this.target = target;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> target.pc;
    }
}
