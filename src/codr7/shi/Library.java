package codr7.shi;

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

    public final void bind(final Symbol key, final IValue value) {
        bindings.put(key, value);
    }

    public final IValue find(final Symbol key) {
        final var v = bindings.get(key);
        return (v == null && parent != null) ? parent.find(key) : v;
    }

    public void importFrom(final Library source, final Symbol...keys) {
        if (keys.length == 0) {
            for (final var e: source.bindings.entrySet()) {
                bind(e.getKey(), e.getValue());
            }
        } else {
            for (final var k: keys) {
                final var v = source.find(k);

                if (v == null) {
                    throw new RuntimeException("Unknown identifier: " + source.name + '/' + k);
                }

                bind(k, v);
            }
        }
    }
}
