package codr7.shi.libraries.core;

import codr7.shi.CellType;
import codr7.shi.ICell;
import codr7.shi.ICellType;
import codr7.shi.VM;

import java.io.PrintStream;

public final class TBool extends CellType<Boolean> {
    public TBool(final String name) {
        super(name);
    }

    @Override
    public boolean asBool(final ICell value) {
        return value.cast(this);
    }

    @Override
    public void dump(final ICell value, final VM vm, final PrintStream out) {
        out.print(value.cast(this) ? 'T' : 'F');
    }
}
