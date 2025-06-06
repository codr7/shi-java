package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Input;
import codr7.shi.Reader;
import codr7.shi.VM;
import codr7.shi.errors.ReadError;
import codr7.shi.forms.ScopeForm;

public class ScopeReader implements Reader {
    public static final ScopeReader INSTANCE = new ScopeReader();

    public boolean read(final VM vm, final Input in, final Forms out) {
        final var formSloc = in.sloc().dup();
        final var body = new Forms();

        if (in.get() != '(') {
            in.unget();
            throw new ReadError(in.sloc(), "Invalid scope");
        }

        for (; ; ) {
            SpaceReader.INSTANCE.read(vm, in, out);
            final var c = in.get();

            if (c == ')') {
                break;
            }

            in.unget();

            if (!vm.readForm(in, body)) {
                throw new ReadError(in.sloc(), "Missing scope end");
            }
        }

        out.pushBack(new ScopeForm(formSloc, body.toArray()));
        return true;
    }
}