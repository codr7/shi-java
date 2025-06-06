package codr7.shi.forms;

import codr7.shi.*;
import codr7.shi.errors.EmitError;

import java.io.PrintStream;

public class IdForm extends Form {
    public final Symbol name;

    public IdForm(final Sloc sloc, final Symbol name) {
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
            throw new EmitError(sloc, "Unknown identifer: " + name);
        }

        v.emit(sloc, in, vm);
    }
}
