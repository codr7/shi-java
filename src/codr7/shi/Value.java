package codr7.shi;

import java.io.PrintStream;

public record Value<T>(ScriptType<T> type, T value) implements IValue {
    @Override
    public boolean asBool() {
        return type.asBool(this);
    }

    @Override
    public void dump(final PrintStream out) {
        type.dump(this, out);
    }

    @Override
    public boolean isa(final IType type) {
        return type.subtypeOf(type);
    }
}
