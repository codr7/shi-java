package codr7.shi.operations;

import codr7.shi.*;
import codr7.shi.errors.EEvaluate;
import codr7.shi.libraries.LCore;

public final class CallMethod implements Operation {
    private final BaseMethod target;
    private final Sloc sloc;

    public CallMethod(final BaseMethod target, final Sloc sloc) {
        this.sloc = sloc;
        this.target = target;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            final var as = target.arguments;
            final var al = as.length;
            final var sl = stack.length();

            if (sl < al) {
                throw new EEvaluate(sloc, "Not enough arguments: " + target.name);
            }

            for (var i = 0; i < al; i++) {
                final var at = target.arguments[i].type();
                final var v = stack.get(sl - i - 1);

                if (at != LCore.Any && !v.isa(at)) {
                    throw new EEvaluate(sloc, "Type mismatch: expected " + at + ", actual " + v.type());
                }
            }

            return target.call(pc + 1, stack, registers, sloc);
        };
    }
}
