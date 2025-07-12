package codr7.shi.forms;

import codr7.shi.*;
import codr7.shi.errors.EEmit;

import java.io.PrintStream;

public class FIdentifier extends Form {
    public final Symbol name;

    public FIdentifier(final Sloc sloc, final Symbol name) {
        super(sloc);
        this.name = name;
    }

    @Override
    public void dump(final VM vm, final PrintStream out) {
        out.print(name.toString());
    }

    @Override
    public void emit(final VM vm, final Forms in) {
        final var v = value(vm);

        if (v == null) {
            throw new EEmit(sloc, "Unknown identifer: " + name);
        }

        v.emit(vm, in, sloc);
    }

    @Override
    public ICell value(final VM vm) {
        return vm.library().find(name);
    }
}
