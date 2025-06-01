package codr7.shi.ops;

import codr7.shi.Label;
import codr7.shi.Op;
import codr7.shi.Stack;
import codr7.shi.VM;

public class Branch implements Op {
    private final Label end;

    public Branch(final Label end) {
        this.end = end;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Stack stack) -> stack.pop().asBool() ? pc + 1 : end.pc;
    }
}
