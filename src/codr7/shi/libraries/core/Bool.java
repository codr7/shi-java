package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.IValue;
import codr7.shi.Symbol;
import codr7.shi.Type;

import java.io.PrintStream;

public final class Bool extends Type<Boolean> {
    public Bool(final Symbol name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final IValue value) {
        return value.cast(this);
    }

    @Override
    public void dump(IValue value, PrintStream out) {
        out.print(value.cast(this) ? 'T' : 'F');
    }
}
