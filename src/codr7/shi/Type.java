package codr7.shi;

import codr7.shi.ops.Push;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Type<T> implements IType {
    private final Symbol name;
    private final Set<IType> superTypes = new HashSet<>();

    public Type(final Symbol name, final IType... superTypes) {
        this.name = name;

        for (final var st : superTypes) {
            st.superTypes().forEach(this.superTypes::add);
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
    public final boolean subtypeOf(final IType other) {
        return superTypes.contains(other);
    }

    @Override
    public final Stream<IType> superTypes() {
        return superTypes.stream();
    }
}
