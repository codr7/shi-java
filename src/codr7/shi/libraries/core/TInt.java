package codr7.shi.libraries.core;

import codr7.shi.ICellType;
import codr7.shi.IValue;
import codr7.shi.CellType;

public final class TInt extends CellType<Long> {
    public TInt(final String name, final ICellType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final IValue value) {
        return value.cast(this) != 0L;
    }
}
