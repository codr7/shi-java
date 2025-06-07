package codr7.shi;

public final class HostMethod extends Method {
    private final Body body;

    public HostMethod(final Symbol name, final Arguments arguments, final Body body) {
        super(name, arguments);
        this.body = body;
    }

    @Override
    public int call(final VM vm, final int pc, final Values stack, final Sloc sloc) {
        body.call(vm, stack, sloc);
        return pc;
    }

    public interface Body {
        void call(VM vm, Values stack, Sloc sloc);
    }
}
