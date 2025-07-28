package codr7.shi.forms;

import codr7.shi.*;
import codr7.shi.operations.PushValue;

import java.io.PrintStream;

public class Literal extends Form {
    public final ICell value;

    public Literal(final Sloc sloc, final ICell value) {
        super(sloc);
        this.value = value;
    }

    @Override
    public void dump(final VM vm, final PrintStream out) {
        value.dump(vm, out);
    }

    @Override
    public void emit(final VM vm, final Forms in) {
        vm.emit(new PushValue(value));
    }

    @Override
    public ICell value(final VM vm) {
        return value;
    }
}
