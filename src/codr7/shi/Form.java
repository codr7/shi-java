package codr7.shi;

import codr7.shi.errors.EEvaluate;

public abstract class Form implements Dumper {
    public final Sloc sloc;

    public Form(final Sloc sloc) {
        this.sloc = sloc;
    }

    public <T extends Form> T cast(final Class<T> expected) {
        final var actual = getClass();

        if (actual != expected) {
            throw new EEvaluate(sloc, "Expected " + expected.getName() + ", actual " + actual.getName());
        }

        return (T) this;
    }

    public abstract void emit(final VM vm, final Forms in);

    public IValue value(final VM vm) {
        return null;
    }

    public <T> T value(final VM vm, CellType<T> type) {
        final var v = value(vm);
        return (v == null) ? null : v.cast(type);
    }

}
