package codr7.shi;

import codr7.shi.errors.EEmit;

public final class HostMacro extends Macro {
    private final Body body;

    public HostMacro(final Symbol name, final String[] args, final Body body) {
        super(name, args);
        this.body = body;
    }

    @Override
    public void call(final VM vm, final Forms in, final Sloc sloc) {
        if (in.length() < args.length) {
            throw new EEmit(sloc, "Not enough arguments");
        }

        body.call(vm, in, sloc);
    }

    public interface Body {
        void call(VM vm, Forms in, Sloc sloc);
    }
}
