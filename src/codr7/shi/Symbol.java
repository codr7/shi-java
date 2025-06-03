package codr7.shi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Symbol {
    public static final ConcurrentMap<String, Symbol> lookup = new ConcurrentHashMap<>();
    public final String name;

    public Symbol(final String name) {
        this.name = name;
    }

    public static Symbol get(final String name) {
        return lookup.computeIfAbsent(name, (k) -> new Symbol(name));
    }

    @Override
    public String toString() {
        return name;
    }
}
