package codr7.shi;

import java.io.PrintStream;

public interface IValue extends Dumper {
    boolean asBool();

    default <T> T cast(final ScriptType<T> type) {
        if (type != this.type()) {
            throw new RuntimeException("Type mismatch: expected " + this.type() + ", actual: " + type);
        }

        return (T) value();
    }

    void emit(VM vm, Forms in, Sloc sloc);

    boolean equals(IValue other);

    boolean isa(IType type);

    IType type();

    Object value();

    void write(final VM vm, final PrintStream out);
}
