package codr7.shi;

import codr7.shi.libraries.LCore;

import java.util.HashMap;
import java.util.Map;

public class Library {
    public final Symbol name;
    public final Library parent;
    public final VM vm;

    private final Map<Symbol, ICell> bindings = new HashMap<>();

    public Library(final VM vm, final Symbol name, final Library parent) {
        this.name = name;
        this.vm = vm;
        this.parent = parent;
    }

    public Library(final VM vm, final String name, final Library parent) {
        this(vm, Symbol.get(name), parent);
    }

    public final void bind(final Symbol key, final ICell value) {
        bindings.put(key, value);
    }

    public final void bind(final String key, final ICell value) {
        bind(Symbol.get(key), value);
    }

    public final <T> void bind(final Symbol key, final CellType<T> type, final T value) {
        bind(key, new Cell<>(type, value));
    }

    public final <T> void bind(final String key, final CellType<T> type, final T value) {
        bind(Symbol.get(key), type, value);
    }

    public final void bindMacro(final String name, final BaseMacro.Arguments arguments, final JavaMacro.Body body) {
        bind(name, LCore.Macro, new JavaMacro(vm, Symbol.get(name), arguments, body));
    }

    public final void bindMethod(final String name, final BaseMethod.Arguments arguments, final JavaMethod.Body body) {
        bind(name, LCore.Method, new JavaMethod(vm, Symbol.get(name), arguments, body));
    }

    public final void bind(final ICellType type) {
        bind(type.name(), LCore.Meta, type);
    }

    public final ICell find(final Symbol key) {
        final var v = bindings.get(key);
        return (v == null && parent != null) ? parent.find(key) : v;
    }

    public void importFrom(final Library source, final Symbol... keys) {
        if (keys.length == 0) {
            for (final var e : source.bindings.entrySet()) {
                bind(e.getKey(), e.getValue());
            }
        } else {
            for (final var k : keys) {
                final var v = source.find(k);

                if (v == null) {
                    throw new RuntimeException("Unknown identifier: " + source.name + '/' + k);
                }

                bind(k, v);
            }
        }
    }
}
