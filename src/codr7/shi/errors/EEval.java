package codr7.shi.errors;

import codr7.shi.Sloc;

public class EEval extends RuntimeException {
    public EEval(final Sloc sloc, final String message) {
        super("Eval Error in " + sloc + ": " + message);
    }
}
