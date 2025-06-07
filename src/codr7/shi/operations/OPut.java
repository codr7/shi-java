package codr7.shi.operations;

import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public final class OPut implements Operation {
    private final int rTarget;
    private final int count;

    public OPut(final int rTarget, final int count) {
        this.rTarget = rTarget;
        this.count = count;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Values stack) -> {
            for (var i = 0; i < count; i++) {
                vm.registers.set(rTarget + i, stack.pop());
            }

            return pc + 1;
        };
    }
}
