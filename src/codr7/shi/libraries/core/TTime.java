package codr7.shi.libraries.core;

import codr7.shi.CellType;
import codr7.shi.ICell;
import codr7.shi.ICellType;

import java.time.Duration;

public final class TTime extends CellType<Duration> {
    public TTime(final String name, final ICellType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final ICell value) {
        return !value.cast(this).isZero();
    }
}
