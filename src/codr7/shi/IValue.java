package codr7.shi;

public interface IValue {
    boolean asBool();

    default <T> T cast(Type<T> type) {
        if (type != this.type()) {
            throw new RuntimeException("Type mismatch: expected " + this.type() + ", actual: " + type);
        }

        return (T) value();
    }

    IType type();

    Object value();
}
