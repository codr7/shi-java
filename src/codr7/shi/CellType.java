package codr7.shi;

import codr7.shi.operations.OPush;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class CellType<T> implements ICellType {
    private final Symbol name;
    private final Set<ICellType> parents = new HashSet<>();

    public CellType(final Symbol name, final ICellType[] parents) {
        this.name = name;

        for (final var st : parents) {
            this.parents.add(st);
            st.parents().forEach(this.parents::add);
        }
    }

    public CellType(final String name, final ICellType[] parents) {
        this(Symbol.get(name), parents);
    }

    @Override
    public void dump(final ICell value, final VM vm, final PrintStream out) {
        out.print(value.cast(this));
    }

    @Override
    public void emit(final ICell value, final VM vm, final Forms in, final Sloc sloc) {
        vm.emit(new OPush(value));
    }

    @Override
    public final Symbol name() {
        return name;
    }

    @Override
    public final Stream<ICellType> parents() {
        return parents.stream();
    }

    @Override
    public final boolean subtypeOf(final ICellType other) {
        return parents.contains(other);
    }

    @Override
    public final String toString() {
        return name.name;
    }
}
