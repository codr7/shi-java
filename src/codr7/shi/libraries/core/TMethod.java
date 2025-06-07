package codr7.shi.libraries.core;

import codr7.shi.*;
import codr7.shi.operations.OCall;

public final class TMethod extends ScriptType<Method> {
    public TMethod(final String name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public void emit(final IValue value, final VM vm, final Forms in, final Sloc sloc) {
        final var m = value.cast(this);

        for (final var a : m.arguments) {
            in.popFront().emit(vm, in);
        }

        vm.emit(new OCall(sloc, m));
    }
}
