package codr7.shi;

public final class ShiMethod extends BaseMethod {
    public final int rArguments;
    private final int startPc;

    public ShiMethod(final Symbol name,
                     final Arguments arguments,
                     final int rArguments,
                     final int startPc) {
        super(name, arguments);
        this.rArguments = rArguments;
        this.startPc = startPc;
    }

    @Override
    public int call(final VM vm, final int pc, final Cells stack, final ICell[] registers, final Sloc sloc) {
        vm.pushCall(this, sloc, pc);
        return startPc;
    }
}
