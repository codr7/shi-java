package codr7.shi;

import codr7.shi.errors.EvalError;

public class HostMethod extends Method {
    private final Body body;

    public HostMethod(final Symbol name, final Arg[] args, final Body body) {
        super(name, args);
        this.body = body;
    }

    @Override
    public int call(final Sloc sloc, final int pc, final Values stack, final VM vm) {
        if (stack.length() < args.length) {
            throw new EvalError(sloc, "Not enough arguments");
        }

        body.call(sloc, stack, vm);
        return pc;
    }

    public interface Body {
        void call(final Sloc sloc, final Values stack, final VM vm);
    }
}
