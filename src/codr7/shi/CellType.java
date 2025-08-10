package codr7.shi;

import codr7.shi.operations.PushValue;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class CellType<T> implements ICellType {
    private final Symbol name;

    public CellType(final Symbol name) {
        this.name = name;
    }

    public CellType(final String name) {
        this(Symbol.get(name));
    }

    @Override
    public void dump(final ICell value, final VM vm, final PrintStream out) {
        out.print(value.cast(this));
    }

    @Override
    public void emit(final ICell value, final VM vm, final Forms in, final Sloc sloc) {
        vm.emit(new PushValue(value));
    }

    @Override
    public final Symbol name() {
        return name;
    }

    @Override
    public final String toString() {
        return name.name;
    }
}
