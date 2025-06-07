package codr7.shi;

import java.util.ArrayList;

public abstract class Macro {
    public final Symbol[] arguments;
    public Symbol name;

    public Macro(final Symbol name, final Arguments arguments) {
        this.name = name;
        this.arguments = arguments.toArray();
    }

    public abstract void call(VM vm, Forms in, Sloc sloc);

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
