package codr7.shi.errors;

import codr7.shi.Sloc;

public class EmitError extends RuntimeException {
    public EmitError(final Sloc sloc, final String message) {
        super("Emit Error in " + sloc + ": " + message);
    }
}
