package codr7.shi.operations;

import codr7.shi.Label;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public class OBranch implements Operation {
    private final Label end;

    public OBranch(final Label end) {
        this.end = end;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Values stack) -> stack.pop().asBool() ? pc + 1 : end.pc;
    }
}
