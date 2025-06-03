package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.IValue;
import codr7.shi.Symbol;
import codr7.shi.Type;

public final class Int extends Type<Integer> {
    public Int(final Symbol name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final IValue value) {
        return value.cast(this) != 0;
    }
}
