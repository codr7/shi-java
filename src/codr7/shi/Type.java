package codr7.shi;

import codr7.shi.operations.Push;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Type<T> implements IType {
    private final Symbol name;
    private final Set<IType> parents = new HashSet<>();

    public Type(final Symbol name, final IType... parents) {
        this.name = name;

        for (final var st : parents) {
            this.parents.add(st);
            st.parents().forEach(this.parents::add);
        }
    }

    @Override
    public void dump(IValue value, PrintStream out) {
        out.print(value.cast(this));
    }

    @Override
    public void emit(final IValue value, final Sloc sloc, final Forms in, final VM vm) {
        vm.emit(new Push(value));
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
