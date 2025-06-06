package codr7.shi.errors;

import codr7.shi.Sloc;

public class ERead extends RuntimeException {
    public ERead(final Sloc sloc, final String message) {
        super("Read Error in " + sloc + ": " + message);
    }
}
