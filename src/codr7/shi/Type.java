package codr7.shi;

import codr7.shi.operations.Push;

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
}
