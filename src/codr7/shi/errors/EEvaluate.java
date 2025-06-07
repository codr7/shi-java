package codr7.shi.errors;

import codr7.shi.Sloc;

public class EEvaluate extends RuntimeException {
    public EEvaluate(final Sloc sloc, final String message) {
        super("Eval Error in " + sloc + ": " + message);
    }
}
