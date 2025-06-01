package codr7.shi;

public abstract class Form {
    public final Sloc sloc;

    public Form(final Sloc sloc) {
        this.sloc = sloc;
    }

    public abstract String dump(VM vm);

    public abstract void emit(Forms in, VM vm);
}
