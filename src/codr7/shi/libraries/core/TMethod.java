package codr7.shi.libraries.core;

import codr7.shi.*;
import codr7.shi.operations.CallMethod;

public final class TMethod extends CellType<BaseMethod> {
    public TMethod(final String name) {
        super(name);
    }

    @Override
    public void emit(final ICell value, final VM vm, final Forms in, final Sloc sloc) {
        final var m = value.cast(this);

        for (final var a : m.arguments) {
            in.popFront().emit(vm, in);
        }

        vm.emit(new CallMethod(m, sloc));
    }
}
