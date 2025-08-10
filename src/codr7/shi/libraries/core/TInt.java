package codr7.shi.libraries.core;

import codr7.shi.CellType;
import codr7.shi.ICell;
import codr7.shi.ICellType;

public final class TInt extends CellType<Long> {
    public TInt(final String name) {
        super(name);
    }

    @Override
    public boolean asBool(final ICell value) {
        return value.cast(this) != 0L;
    }
}
