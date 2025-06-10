package codr7.shi.operations;

import codr7.shi.IValue;
import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Cells;

public final class OPush implements Operation {
    private final IValue value;

    public OPush(final IValue value) {
        this.value = value;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final IValue[] registers) -> {
            stack.push(value.dup());
            return pc + 1;
        };
    }
}
