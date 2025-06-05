package codr7.shi;

public final class Sloc {
    public final String source;
    private int line, column;

    private Sloc(final String source, final int line, final int column) {
        this.source = source;
        this.line = line;
        this.column = column;
    }

    public Sloc(final String source) {
        this(source, 1, 1);
    }

    public Sloc dup() {
        return new Sloc(source, line, column);
    }

    public int line() {
        return line;
    }

    public char step(final char c) {
        if (c == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }

        return c;
    }

    public String toString() {
        return String.format("'%s' at line %d, column %d", source, line, column);
    }
}
