package codr7.shi.forms;

import codr7.shi.Form;
import codr7.shi.Forms;
import codr7.shi.Sloc;
import codr7.shi.VM;

import java.io.PrintStream;

public class ScopeForm extends Form {
    public final Form[] body;

    public ScopeForm(final Sloc sloc, final Form[] body) {
        super(sloc);
        this.body = body;
    }

    @Override
    public void dump(final PrintStream out, final VM vm) {
        out.print('(');

        for (var i = 0; i < body.length; i++) {
            if (i > 0) {
                out.print(' ');
            }

            body[i].dump(out, vm);
        }

        out.print(')');
    }

    @Override
    public void emit(final Forms in, final VM vm) {
        vm.withLibrary(null, () -> {
            for (final var f : body) {
                f.emit(in, vm);
            }
        });
    }
}
