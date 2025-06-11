package codr7.shi;

import java.io.PrintStream;
import java.util.ArrayList;

public final class Cells {
    private final ArrayList<ICell> items = new ArrayList<>();

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

    public ICell get(final int index) {
        return items.get(index);
    }

    public int length() {
        return items.size();
    }

    public ICell peek() {
        return items.getLast();
    }

    public ICell pop() {
        return items.removeLast();
    }

    public void push(final ICell it) {
        items.add(it);
    }

    public <T> void push(final CellType<T> type, final T value) {
        push(new Cell<>(type, value));
    }
}
