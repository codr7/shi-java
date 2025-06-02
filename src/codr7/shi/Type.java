package codr7.shi;

public abstract class Type<T> implements IType {
    private final Symbol name;

    public Type(final Symbol name) {
        this.name = name;
    }

    public final Symbol name() {
        return name;
    }
}
