package codr7.shi.readers;

import codr7.shi.*;
import codr7.shi.errors.ERead;
import codr7.shi.forms.FId;

public class RId implements Reader {
    public static final RId INSTANCE = new RId();

    public boolean read(final VM vm, final Input in, final Forms out) {
        final var formSloc = in.sloc().dup();
        final var buf = new StringBuilder();

        for (; ; ) {
            final var c = in.get();

            if (c == -1 || Character.isWhitespace(c) || c == '(' || c == ')') {
                in.unget();
                break;
            }

            buf.append((char) c);
        }

        if (buf.isEmpty()) {
            throw new ERead(formSloc, "Invalid id");
        }

        out.pushBack(new FId(formSloc, Symbol.get(buf.toString())));
        return true;
    }
}