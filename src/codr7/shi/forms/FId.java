package codr7.shi.forms;

import codr7.shi.*;
import codr7.shi.errors.EEmit;

import java.io.PrintStream;

public class FId extends Form {
    public final Symbol name;

    public FId(final Sloc sloc, final Symbol name) {
        super(sloc);
        this.name = name;
    }

    @Override
    public void dump(final PrintStream out, final VM vm) {
        out.print(name.toString());
    }

    @Override
    public void emit(final Forms in, final VM vm) {
        final var v = vm.currentLibrary().find(name);

        if (v == null) {
            throw new EEmit(sloc, "Unknown identifer: " + name);
        }

        v.emit(vm, in, sloc);
    }
}
