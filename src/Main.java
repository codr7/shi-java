import codr7.shi.REPL;
import codr7.shi.Symbol;
import codr7.shi.VM;
import codr7.shi.Values;
import codr7.shi.libraries.Core;

public class Main {
    public static void main(String[] args) {
        final var vm = new VM();
        final var core = new Core(Symbol.get("core"), null);
        vm.currentLibrary().importFrom(core);
        new REPL(vm, System.in, System.out).run(new Values());
    }
}