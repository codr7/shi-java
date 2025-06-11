package codr7.shi.operations;

import codr7.shi.*;

public final class OBranch implements Operation {
    private final Label end;

    public OBranch(final Label end) {
        this.end = end;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> stack.pop().asBool() ? pc + 1 : end.pc;
    }
}
