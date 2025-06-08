package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.IValue;
import codr7.shi.ScriptType;

import java.time.Duration;

public final class TTime extends ScriptType<Duration> {
    public TTime(final String name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public boolean asBool(final IValue value) {
        return !value.cast(this).isZero();
    }
}
