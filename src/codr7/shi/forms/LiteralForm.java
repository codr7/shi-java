package codr7.shi.forms;

import codr7.shi.*;
import codr7.shi.operations.Push;

import java.io.PrintStream;

public class LiteralForm extends Form {
    public final IValue value;

    public LiteralForm(final Sloc sloc, final IValue value) {
        super(sloc);
        this.value = value;
    }

    @Override
    public void dump(final PrintStream out, final VM vm) {
        value.dump(out);
    }

    @Override
    public void emit(final Forms in, final VM vm) {
        vm.emit(new Push(value));
    }
}
