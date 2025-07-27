package codr7.shi.libraries.core;

import codr7.shi.*;
import codr7.shi.operations.GetRegister;

import java.io.PrintStream;

public final class TBinding extends CellType<Integer> {
    public TBinding(final String name, final ICellType... parents) {
        super(name, parents);
    }

    @Override
    public void dump(final ICell value, final VM vm, final PrintStream out) {
        out.print("#" + value.cast(this));
    }

    @Override
    public void emit(final ICell value, final VM vm, final Forms in, final Sloc sloc) {
        vm.emit(new GetRegister(value.cast(this), 1));
    }
}
