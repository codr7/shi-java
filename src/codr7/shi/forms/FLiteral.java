package codr7.shi.forms;

import codr7.shi.*;
import codr7.shi.operations.OPush;

import java.io.PrintStream;

public class FLiteral extends Form {
    public final IValue value;

    public FLiteral(final Sloc sloc, final IValue value) {
        super(sloc);
        this.value = value;
    }

    @Override
    public void dump(final PrintStream out, final VM vm) {
        value.dump(out);
    }

    @Override
    public void emit(final Forms in, final VM vm) {
        vm.emit(new OPush(value));
    }
}
