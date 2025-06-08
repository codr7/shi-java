package codr7.shi.operations;

import codr7.shi.*;
import codr7.shi.errors.EEvaluate;

public final class OCheck implements Operation {
    private final IValue expected;
    private final Sloc sloc;

    public OCheck(final IValue expected, final Sloc sloc) {
        this.expected = expected;
        this.sloc = sloc;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Values stack, final IValue[] registers) -> {
            final var actual = stack.pop();

            if (!actual.equals(expected)) {
                throw new EEvaluate(sloc, "Check failed, expected " + expected.dump(vm) + ", actual " + actual.dump(vm));
            }

            return pc + 1;
        };
    }
}
