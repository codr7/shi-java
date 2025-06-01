package codr7.shi.ops;

import codr7.shi.IValue;
import codr7.shi.Op;
import codr7.shi.VM;
import codr7.shi.Values;

public class Push implements Op {
    private final IValue value;

    public Push(final IValue value) {
        this.value = value;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Values stack) -> {
            stack.push(value);
            return pc + 1;
        };
    }
}
