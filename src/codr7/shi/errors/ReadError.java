package codr7.shi.errors;

import codr7.shi.Sloc;

public class ReadError extends RuntimeException {
    public ReadError(final Sloc sloc, final String message) {
        super("Read Error in " + sloc + ": " + message);
    }
}
