package codr7.shi;

public final class ScriptMethod extends Method {
    private final int startPc;

    public ScriptMethod(final Symbol name, final Arguments arguments, final int startPc) {
        super(name, arguments);
        this.startPc = startPc;
    }

    @Override
    public int call(final VM vm, final int pc, final Values stack, final Sloc sloc) {
        vm.pushCall(sloc, this, pc);
        return startPc;
    }
}
