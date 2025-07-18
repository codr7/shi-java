package codr7.shi.operations;

import codr7.shi.*;
import codr7.shi.errors.EEvaluate;

public final class CheckValue implements Operation {
    private final ICell expected;
    private final Sloc sloc;

    public CheckValue(final ICell expected, final Sloc sloc) {
        this.expected = expected;
        this.sloc = sloc;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            final var actual = stack.pop();

            if (!actual.equals(expected)) {
                throw new EEvaluate(sloc, "Check failed, expected " + expected.dump(vm) + ", actual " + actual.dump(vm));
            }

            return pc + 1;
        };
    }
}
