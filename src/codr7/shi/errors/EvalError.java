package codr7.shi.errors;

import codr7.shi.Sloc;

public class EvalError extends RuntimeException {
    public EvalError(final Sloc sloc, final String message) {
        super("Eval Error in " + sloc + ": " + message);
    }
}
