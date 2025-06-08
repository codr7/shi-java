package codr7.shi;

import java.io.PrintStream;
import java.util.ArrayList;

public final class Values {
    private final ArrayList<IValue> items = new ArrayList<>();

    public void clear() {
        items.clear();
    }

    public void dump(final VM vm, final PrintStream out) {
        out.print('[');
        for (var i = 0; i < items.size(); i++) {
            if (i > 0) {
                out.print(' ');
            }

            items.get(i).dump(vm, out);
        }
        out.print(']');
    }

    public IValue get(final int index) {
        return items.get(index);
    }

    public int length() {
        return items.size();
    }

    public IValue peek() {
        return items.getLast();
    }

    public IValue pop() {
        return items.removeLast();
    }

    public void push(final IValue it) {
        items.add(it);
    }

    public <T> void push(final ScriptType<T> type, final T value) {
        push(new Value<>(type, value));
    }
}
