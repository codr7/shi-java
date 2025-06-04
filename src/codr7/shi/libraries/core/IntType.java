package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.IValue;
import codr7.shi.ScriptType;
import codr7.shi.Symbol;

public final class IntType extends ScriptType<Integer> {
    public IntType(final Symbol name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final IValue value) {
        return value.cast(this) != 0;
    }
}
