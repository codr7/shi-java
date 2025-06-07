package codr7.shi;

import codr7.shi.operations.OPush;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class ScriptType<T> implements IType {
    private final Symbol name;
    private final Set<IType> parents = new HashSet<>();

    public ScriptType(final Symbol name, final IType[] parents) {
        this.name = name;

        for (final var st : parents) {
            this.parents.add(st);
            st.parents().forEach(this.parents::add);
        }
    }

    public ScriptType(final String name, final IType[] parents) {
        this(Symbol.get(name), parents);
    }

    @Override
    public void dump(final IValue value, final VM vm, final PrintStream out) {
        out.print(value.cast(this));
    }

    @Override
    public void emit(final IValue value, final VM vm, final Forms in, final Sloc sloc) {
        vm.emit(new OPush(value));
    }

    @Override
    public final Symbol name() {
        return name;
    }

    @Override
    public final Stream<IType> parents() {
        return parents.stream();
    }

    @Override
    public final boolean subtypeOf(final IType other) {
        return parents.contains(other);
    }

    @Override
    public final String toString() {
        return name.name;
    }
}
