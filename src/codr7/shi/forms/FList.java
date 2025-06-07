package codr7.shi.forms;

import codr7.shi.Form;
import codr7.shi.Forms;
import codr7.shi.Sloc;
import codr7.shi.VM;

import java.io.PrintStream;

public class FList extends Form {
    public final Form[] body;

    public FList(final Sloc sloc, final Form[] body) {
        super(sloc);
        this.body = body;
    }

    @Override
    public void dump(final VM vm, final PrintStream out) {
        out.print('(');

        for (var i = 0; i < body.length; i++) {
            if (i > 0) {
                out.print(' ');
            }

            body[i].dump(vm, out);
        }

        out.print(')');
    }

    @Override
    public void emit(final VM vm, final Forms in) {
        vm.withLibrary(null, () -> {
            new Forms(body).emit(vm);
        });
    }
}
