package codr7.shi;

import java.io.PrintStream;
import java.util.stream.Stream;

public interface ICellType {
    default boolean asBool(final ICell value) {
        return true;
    }

    void dump(ICell value, VM vm, PrintStream out);

    void emit(ICell value, VM vm, Forms in, Sloc sloc);

    default boolean equals(ICell left, ICell right) {
        return left.value().equals(right.value());
    }

    Symbol name();

    Stream<ICellType> parents();

    boolean subtypeOf(ICellType other);

    default void write(final ICell value, final VM vm, final PrintStream out) {
        dump(value, vm, out);
    }
}