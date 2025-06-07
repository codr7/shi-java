package codr7.shi;

public abstract class Macro {
    public final String[] args;
    public Symbol name;

    public Macro(final Symbol name, final String[] args) {
        this.name = name;
        this.args = args;
    }

    public abstract void call(VM vm, Forms in, Sloc sloc);
}
