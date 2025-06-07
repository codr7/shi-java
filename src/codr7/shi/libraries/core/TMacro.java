package codr7.shi.libraries.core;

import codr7.shi.*;

public final class TMacro extends ScriptType<Macro> {
    public TMacro(final Symbol name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public void emit(final IValue value, final VM vm, final Forms in, final Sloc sloc) {
        value.cast(this).call(vm, in, sloc);
    }
}
