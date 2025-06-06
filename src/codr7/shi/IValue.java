package codr7.shi;

import java.io.PrintStream;

public interface IValue {
    boolean asBool();

    default <T> T cast(final ScriptType<T> type) {
        if (type != this.type()) {
            throw new RuntimeException("Type mismatch: expected " + this.type() + ", actual: " + type);
        }

        return (T) value();
    }

    void dump(PrintStream out);

    void emit(Sloc sloc, Forms in, VM vm);

    boolean isa(IType type);

    IType type();

    Object value();
}
