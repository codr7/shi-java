package codr7.shi;

import codr7.shi.errors.EEmit;

public final class JavaMacro extends BaseMacro {
    private final Body body;

    public JavaMacro(final Symbol name, final Arguments arguments, final Body body) {
        super(name, arguments);
        this.body = body;
    }

    @Override
    public void call(final VM vm, final Forms in, final Sloc sloc) {
        if (in.length() < arguments.length) {
            throw new EEmit(sloc, "Not enough arguments: " + name);
        }

        body.call(vm, in, sloc);
    }

    public interface Body {
        void call(VM vm, Forms in, Sloc sloc);
    }
}
