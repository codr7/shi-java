package codr7.shi;

import java.util.ArrayList;

public abstract class Method {
    public final Argument[] arguments;
    public Symbol name;

    public Method(final Symbol name, final Arguments arguments) {
        this.name = name;
        this.arguments = arguments.toArray();
    }

    public abstract int call(VM vm, int pc, Values stack, Sloc sloc);

    public record Argument(Symbol name, IType type) {
    }

    public static final class Arguments {
        private final ArrayList<Argument> items = new ArrayList<>();

        public Method.Arguments add(final Symbol name, IType type) {
            items.add(new Argument(name, type));
            return this;
        }

        public Method.Arguments add(final String name, IType type) {
            return add(Symbol.get(name), type);
        }

        public Argument[] toArray() {
            return items.toArray(new Argument[0]);
        }
    }
}
