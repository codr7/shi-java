package codr7.shi.errors;

import codr7.shi.Sloc;

public class EEmit extends RuntimeException {
    public EEmit(final Sloc sloc, final String message) {
        super("Emit Error in " + sloc + ": " + message);
    }
}
