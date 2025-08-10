package codr7.shi;

import codr7.shi.libraries.Core;

import java.io.PrintStream;

public record Cell<T>(CellType<T> type, T value) implements ICell {
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
    public boolean equals(final ICell other) {
        return other.type() == type && type.equals(this, other);
    }

    @Override
    public boolean isa(final ICellType type) {
        return type == Core.Any || this.type == type;
    }
}
