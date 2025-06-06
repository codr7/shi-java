package codr7.shi.libraries.core;

import codr7.shi.*;
import codr7.shi.operations.OCall;

public final class TMethod extends ScriptType<Method> {
    public TMethod(final Symbol name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public void emit(final IValue value, final Sloc sloc, final Forms in, final VM vm) {
        final var m = value.cast(this);
        final var emitArgs = new Forms();

        for (final var a : m.args) {
            in.popFront().emit(emitArgs, vm);
        }

        vm.emit(new OCall(sloc, m));
    }
}
