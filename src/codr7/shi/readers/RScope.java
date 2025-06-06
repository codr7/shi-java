package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Input;
import codr7.shi.Reader;
import codr7.shi.VM;
import codr7.shi.errors.ERead;
import codr7.shi.forms.FScope;

public class RScope implements Reader {
    public static final RScope INSTANCE = new RScope();

    public boolean read(final VM vm, final Input in, final Forms out) {
        final var formSloc = in.sloc().dup();
        final var body = new Forms();

        if (in.get() != '(') {
            in.unget();
            throw new ERead(in.sloc(), "Invalid scope");
        }

        for (; ; ) {
            RSpace.INSTANCE.read(vm, in, out);
            final var c = in.get();

            if (c == ')') {
                break;
            }

            in.unget();

            if (!vm.readForm(in, body)) {
                throw new ERead(in.sloc(), "Missing scope end");
            }
        }

        out.pushBack(new FScope(formSloc, body.toArray()));
        return true;
    }
}