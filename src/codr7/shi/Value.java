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

    public void emit(final Sloc sloc, final Forms in, final VM vm) {
        type.emit(this, sloc, in, vm);
    }

    @Override
    public boolean isa(final IType type) {
        return this.type == type || type.subtypeOf(type);
    }
}
