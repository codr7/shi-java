package codr7.shi.operations;

import codr7.shi.Label;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public class OGoto implements Operation {
    private final Label target;

    public OGoto(final Label target) {
        this.target = target;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Values stack) -> target.pc;
    }
}
