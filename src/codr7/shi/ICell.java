package codr7.shi;

import java.io.PrintStream;

public interface ICell extends Dumper {
    boolean asBool();

    default <T> T cast(final CellType<T> type) {
        if (type != this.type()) {
            throw new RuntimeException("Type mismatch: expected " + this.type() + ", actual: " + type);
        }

        return (T) value();
    }

    void emit(VM vm, Forms in, Sloc sloc);

    boolean equals(ICell other);

    boolean isa(ICellType type);

    ICellType type();

    Object value();

    void write(final VM vm, final PrintStream out);
}
