package codr7.shi.libraries.core;

import codr7.shi.*;

public final class TMacro extends CellType<BaseMacro> {
    public TMacro(final String name, final ICellType... parents) {
        super(name, parents);
    }

    @Override
    public void emit(final ICell value, final VM vm, final Forms in, final Sloc sloc) {
        value.cast(this).call(vm, in, sloc);
    }
}
