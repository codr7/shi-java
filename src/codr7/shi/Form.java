package codr7.shi;

import java.io.PrintStream;

public abstract class Form {
    public final Sloc sloc;

    public Form(final Sloc sloc) {
        this.sloc = sloc;
    }

    public abstract void dump(final PrintStream out, final VM vm);

    public abstract void emit(final Forms in, final VM vm);
}
