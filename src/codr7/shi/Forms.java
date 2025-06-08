package codr7.shi;

import java.util.ArrayDeque;
import java.util.Arrays;

public final class Forms {
    private final ArrayDeque<Form> items = new ArrayDeque<>();

    public Forms() {
    }

    public Forms(final Form[] items) {
        this.items.addAll(Arrays.asList(items));
    }

    public void emit(final VM vm) {
        while (!items.isEmpty()) {
            popFront().emit(vm, this);
        }
    }

    public int length() {
        return items.size();
    }

    public Form peekFront() {
        return items.getFirst();
    }

    public Form popFront() {
        return items.removeFirst();
    }

    public void pushBack(final Form it) {
        items.addLast(it);
    }

    public Form[] toArray() {
        return items.toArray(new Form[0]);
    }
}
