package codr7.shi;

public final class JavaMethod extends BaseMethod {
    private final Body body;

    public JavaMethod(final VM vm, final Symbol name, final Arguments arguments, final Body body) {
        super(vm, name, arguments);
        this.body = body;
    }

    @Override
    public int call(final int pc, final Cells stack, final ICell[] registers, final Sloc sloc) {
        body.call(stack, registers, sloc);
        return pc;
    }

    public interface Body {
        void call(Cells stack, ICell[] registers, Sloc sloc);
    }
}
