package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.Symbol;
import codr7.shi.Type;

public final class Meta extends Type<IType> {
    public Meta(final Symbol name, final IType... parents) {
        super(name, parents);
    }
}
