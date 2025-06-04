package codr7.shi;

public interface IValue {
    boolean asBool();

    default <T> T cast(ScriptType<T> type) {
        if (type != this.type()) {
            throw new RuntimeException("Type mismatch: expected " + this.type() + ", actual: " + type);
        }

        return (T) value();
    }

    boolean isa(IType type);

    IType type();

    Object value();
}
