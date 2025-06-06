package codr7.shi.operations;

import codr7.shi.*;
import codr7.shi.errors.EEval;

public class OCall implements Operation {
    private final Sloc sloc;
    private final Method target;

    public OCall(final Sloc sloc, final Method target) {
        this.sloc = sloc;
        this.target = target;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Values stack) -> {
            final var as = target.args;
            final var al = as.length;
            final var sl = stack.length();

            if (sl < al) {
                throw new EEval(sloc, "Not enough args");
            }

            for (var i = 0; i < al; i++) {
                final var at = target.args[i].type();
                final var v = stack.get(sl - i - 1);

                if (!v.isa(at)) {
                    throw new EEval(sloc, "Type mismatch: expected " + at + ", actual " + v.type());
                }
            }

            return target.call(sloc, pc + 1, stack, vm);
        };
    }
}
