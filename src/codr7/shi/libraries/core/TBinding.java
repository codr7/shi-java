package codr7.shi.libraries.core;

import codr7.shi.IType;
import codr7.shi.IValue;
import codr7.shi.ScriptType;
import codr7.shi.VM;

import java.io.PrintStream;

public final class TBinding extends ScriptType<Integer> {
    public TBinding(final String name, final IType... parents) {
        super(name, parents);
    }

    @Override
    public void dump(final IValue value, final VM vm, final PrintStream out) {
        out.print("#" + value.cast(this));
    }
}
