package codr7.shi.operations;

import codr7.shi.IValue;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public final class OPush implements Operation {
    private final IValue value;

    public OPush(final IValue value) {
        this.value = value;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Values stack) -> {
            stack.push(value);
            return pc + 1;
        };
    }
}
