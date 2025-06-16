package codr7.shi;

import java.util.ArrayList;

public abstract class BaseMethod {
    public final Argument[] arguments;
    public final Symbol name;
    public final VM vm;

    public BaseMethod(final VM vm, final Symbol name, final Arguments arguments) {
        this.name = name;
        this.arguments = arguments.toArray();
        this.vm = vm;
    }

    public abstract int call(int pc, Cells stack, ICell[] registers, Sloc sloc);

    public record Argument(Symbol name, ICellType type) {
    }

    public static final class Arguments {
        private final ArrayList<Argument> items = new ArrayList<>();

        public BaseMethod.Arguments add(final Symbol name, ICellType type) {
            items.add(new Argument(name, type));
            return this;
        }

        public BaseMethod.Arguments add(final String name, ICellType type) {
            return add(Symbol.get(name), type);
        }

        public int length() {
            return items.size();
        }

        public Argument[] toArray() {
            return items.toArray(new Argument[0]);
        }
    }
}
