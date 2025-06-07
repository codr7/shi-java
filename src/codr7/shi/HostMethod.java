package codr7.shi;

import codr7.shi.errors.EEval;

public final class HostMethod extends Method {
    private final Body body;

    public HostMethod(final Symbol name, final Arg[] args, final Body body) {
        super(name, args);
        this.body = body;
    }

    @Override
    public int call(final VM vm, final int pc, final Values stack, final Sloc sloc) {
        if (stack.length() < args.length) {
            throw new EEval(sloc, "Not enough arguments");
        }

        body.call(vm, stack, sloc);
        return pc;
    }

    public interface Body {
        void call(VM vm, Values stack, Sloc sloc);
    }
}
