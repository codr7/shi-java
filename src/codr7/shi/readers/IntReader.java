package codr7.shi.readers;

import codr7.shi.*;
import codr7.shi.forms.LiteralForm;
import codr7.shi.libraries.Core;

public class IntReader implements Reader {
    public static final IntReader INSTANCE = new IntReader();

    public boolean read(final VM vm, final Input in, final Forms out) {
        final var formSloc = in.sloc().dup();
        var v = 0;

        for (; ; ) {
            final var c = in.get();

            if (!Character.isDigit(c)) {
                in.unget();
                break;
            }

            v = v * 10 + c - '0';
        }

        out.pushBack(new LiteralForm(formSloc, new Value<>(Core.INT, v)));
        return true;
    }
}