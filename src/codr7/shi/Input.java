package codr7.shi;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

public class Input {
    private final PushbackReader reader;
    private final Sloc sloc;
    private int lastChar = -1;

    public Input(final Reader in, final Sloc sloc) {
        reader = new PushbackReader(in);
        this.sloc = sloc;
    }

    public int get() {
        try {
            if (lastChar != -1) {
                sloc.step(lastChar);
            }

            lastChar = reader.read();

            if (lastChar == 65535) {
                lastChar = -1;
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return lastChar;
    }

    public Sloc sloc() {
        return sloc;
    }

    public void unget() {
        assert (lastChar != -1);

        try {
            reader.unread(lastChar);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        lastChar = -1;
    }
}
