package codr7.shi;

public abstract class Method {
    public final Arg[] args;
    public Symbol name;

    public Method(final Symbol name, final Arg[] args) {
        this.name = name;
        this.args = args;
    }

    public abstract int call(final Sloc sloc, final int pc, final Values stack, final VM vm);

    public record Arg(String name, IType type) {
    }
}
