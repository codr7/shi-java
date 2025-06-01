package codr7.shi;

public record Value<T>(Type<T> type, T value) implements IValue {
    @Override
    public boolean asBool() {
        return type().asBool(this);
    }
}
