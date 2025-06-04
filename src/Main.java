import codr7.shi.Symbol;
import codr7.shi.VM;
import codr7.shi.libraries.Core;

public class Main {
    public static void main(String[] args) {
        final var vm = new VM();
        final var core = new Core(Symbol.get("core"), null);
        vm.currentLibrary().importFrom(core);
    }
}