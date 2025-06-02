package codr7.shi.operations;

import codr7.shi.Operation;
import codr7.shi.VM;
import codr7.shi.Values;

public class Get implements Operation {
    private final int rSource;

    public Get(final int rSource) {
        this.rSource = rSource;
    }

    @Override
    public Eval compile(final VM vm, final int pc) {
        return (final Values stack) -> {
            stack.push(vm.registers.get(rSource));
            return pc + 1;
        };
    }
}
