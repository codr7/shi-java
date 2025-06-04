package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.ScriptType;
import codr7.shi.Symbol;

public final class MetaType extends ScriptType<IType> {
    public MetaType(final Symbol name, final IType... parents) {
        super(name, parents);
    }
}
