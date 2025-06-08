package codr7.shi;

import java.io.PrintStream;

public record Value<T>(ScriptType<T> type, T value) implements IValue {
    @Override
    public boolean asBool() {
        return type.asBool(this);
    }

    @Override
    public void dump(final VM vm, final PrintStream out) {
        type.dump(this, vm, out);
    }

    public void emit(final VM vm, final Forms in, final Sloc sloc) {
        type.emit(this, vm, in, sloc);
    }

    @Override
    public boolean isa(final IType type) {
        return this.type == type || this.type.subtypeOf(type);
    }

    @Override
    public void write(final VM vm, final PrintStream out) {
        type.write(this, vm, out);
    }

}
