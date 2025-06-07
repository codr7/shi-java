package codr7.shi;

public abstract class Method {
    public final Arg[] args;
    public Symbol name;

    public Method(final Symbol name, final Arg[] args) {
        this.name = name;
        this.args = args;
    }

    public abstract int call(VM vm, int pc, Values stack, Sloc sloc);

    public record Arg(String name, IType type) {
    }
}
