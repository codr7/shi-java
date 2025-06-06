package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.IValue;
import codr7.shi.ScriptType;
import codr7.shi.Symbol;

import java.io.PrintStream;

public final class TBool extends ScriptType<Boolean> {
    public TBool(final Symbol name, final IType... parents) {
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
