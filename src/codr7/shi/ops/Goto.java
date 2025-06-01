package codr7.shi.ops;

import codr7.shi.Label;
import codr7.shi.Op;
import codr7.shi.VM;
import codr7.shi.Values;

public class Goto implements Op {
    private final Label target;

    public Goto(final Label target) {
        this.target = target;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Values stack) -> target.pc;
    }
}
