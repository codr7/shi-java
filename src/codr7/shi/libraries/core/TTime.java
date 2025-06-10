package codr7.shi.libraries.core;

import codr7.shi.ICellType;
import codr7.shi.IValue;
import codr7.shi.CellType;

import java.time.Duration;

public final class TTime extends CellType<Duration> {
    public TTime(final String name, final ICellType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final IValue value) {
        return !value.cast(this).isZero();
    }
}
