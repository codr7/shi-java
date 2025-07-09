package codr7.shi.readers;

import codr7.shi.*;
import codr7.shi.errors.ERead;
import codr7.shi.forms.FIdentifier;

public class RIdentifier implements Reader {
    public static final RIdentifier INSTANCE = new RIdentifier();

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

        out.pushBack(new FIdentifier(formSloc, Symbol.get(buf.toString())));
        return true;
    }
}