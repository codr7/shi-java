package codr7.shi.operations;

import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public class Put implements Operation {
    private final int rTarget;

    public Put(final int rTarget) {
        this.rTarget = rTarget;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Values stack) -> {
            vm.registers.set(rTarget, stack.pop());
            return pc + 1;
        };
    }
}
