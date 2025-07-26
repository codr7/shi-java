import codr7.shi.Cells;
import codr7.shi.Shell;
import codr7.shi.VM;

import java.nio.file.Path;

public class Main {
    public static void main(final String[] arguments) {
        final var vm = new VM();

        if (arguments.length == 0) {
            new Shell(vm, System.in, System.out).run(new Cells());
        } else {
            final var startPc = vm.emitPc();

            for (final var a : arguments) {
                vm.load(Path.of(a));
            }

            vm.evaluate(startPc, -1, new Cells());
        }
    }
}