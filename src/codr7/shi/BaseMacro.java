package codr7.shi;

import java.util.ArrayList;

public abstract class BaseMacro {
    public final Symbol[] arguments;
    public final Symbol name;
    public final VM vm;

    public BaseMacro(final VM vm, final Symbol name, final Arguments arguments) {
        this.name = name;
        this.arguments = arguments.toArray();
        this.vm = vm;
    }

    public abstract void call(Forms in, Sloc sloc);

    public static final class Arguments {
        private final ArrayList<Symbol> items = new ArrayList<>();

        public Arguments add(final Symbol name) {
            items.add(name);
            return this;
        }

        public Arguments add(final String name) {
            return add(Symbol.get(name));
        }

        public Symbol[] toArray() {
            return items.toArray(new Symbol[0]);
        }
    }
}
