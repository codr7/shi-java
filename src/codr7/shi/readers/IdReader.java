package codr7.shi.readers;

import codr7.shi.*;
import codr7.shi.errors.ReadError;
import codr7.shi.forms.IdForm;

public class IdReader implements Reader {
    public static final IdReader INSTANCE = new IdReader();

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
            throw new ReadError(formSloc, "Invalid id");
        }

        out.pushBack(new IdForm(formSloc, Symbol.get(buf.toString())));
        return true;
    }
}