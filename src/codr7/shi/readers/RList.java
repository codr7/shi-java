package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Input;
import codr7.shi.Reader;
import codr7.shi.VM;
import codr7.shi.errors.ERead;
import codr7.shi.forms.FList;

public class RList implements Reader {
    public static final RList INSTANCE = new RList();

    public boolean read(final VM vm, final Input in, final Forms out) {
        final var formSloc = in.sloc().dup();
        final var body = new Forms();

        if (in.get() != '(') {
            in.unget();
            throw new ERead(in.sloc(), "Invalid scope");
        }

        for (; ; ) {
            RWhitespace.INSTANCE.read(vm, in, out);
            final var c = in.get();

            if (c == ')') {
                break;
            }

            in.unget();

            if (!vm.readForm(in, body)) {
                throw new ERead(in.sloc(), "Missing scope end");
            }
        }

        out.pushBack(new FList(formSloc, body.toArray()));
        return true;
    }
}