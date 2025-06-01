package codr7.shi.ops;

import codr7.shi.Op;
import codr7.shi.Stack;
import codr7.shi.VM;

public class Put implements Op {
    private final int rTarget;

    public Put(final int rTarget) {
        this.rTarget = rTarget;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Stack stack) -> {
            vm.registers.set(rTarget, stack.pop());
            return pc + 1;
        };
    }
}
