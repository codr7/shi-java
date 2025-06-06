import codr7.shi.REPL;
import codr7.shi.VM;
import codr7.shi.Values;
import codr7.shi.libraries.LCore;

public class Main {
    public static void main(final String[] args) {
        final var vm = new VM();
        final var core = new LCore();
        vm.currentLibrary().importFrom(core);
        new REPL(vm, System.in, System.out).run(new Values());
    }
}