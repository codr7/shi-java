package codr7.shi;

import codr7.shi.libraries.LCore;

import java.util.HashMap;
import java.util.Map;

public class Library {
    public final Symbol name;
    private final Map<Symbol, IValue> bindings = new HashMap<>();
    private final Library parent;

    public Library(final Symbol name, final Library parent) {
        this.name = name;
        this.parent = parent;
    }

    public Library(final String name, final Library parent) {
        this(Symbol.get(name), parent);
    }

    public final void bind(final Symbol key, final IValue value) {
        bindings.put(key, value);
    }

    public final void bind(final String key, final IValue value) {
        bind(Symbol.get(key), value);
    }

    public final <T> void bind(final Symbol key, final CellType<T> type, final T value) {
        bind(key, new Cell<>(type, value));
    }

    public final <T> void bind(final String key, final CellType<T> type, final T value) {
        bind(Symbol.get(key), type, value);
    }

    public final void bindMacro(final String name, final Macro.Arguments arguments, final HostMacro.Body body) {
        bind(name, LCore.Macro, new HostMacro(Symbol.get(name), arguments, body));
    }

    public final void bindMethod(final String name, final Method.Arguments arguments, final JavaMethod.Body body) {
        bind(name, LCore.Method, new JavaMethod(Symbol.get(name), arguments, body));
    }

    public final void bind(final ICellType type) {
        bind(type.name(), LCore.Meta, type);
    }

    public final IValue find(final Symbol key) {
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
