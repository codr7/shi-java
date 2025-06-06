package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.ScriptType;
import codr7.shi.Symbol;

public final class TMeta extends ScriptType<IType> {
    public TMeta(final Symbol name, final IType... parents) {
        super(name, parents);
    }
}
