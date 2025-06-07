package codr7.shi;

import java.io.PrintStream;
import java.util.stream.Stream;

public interface IType {
    default boolean asBool(final IValue value) {
        return true;
    }

    void dump(IValue value, VM vm, PrintStream out);

    default IValue dup(final IValue value, final VM vm) {
        return value;
    }

    void emit(IValue value, VM vm, Forms in, Sloc sloc);

    Symbol name();

    Stream<IType> parents();

    boolean subtypeOf(IType other);

    default void write(final IValue value, final VM vm, final PrintStream out) {
        dump(value, vm, out);
    }
}