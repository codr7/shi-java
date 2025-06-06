package codr7.shi;

import java.util.ArrayDeque;

final public class Forms {
    private final ArrayDeque<Form> items = new ArrayDeque<>();

    public void emit(final VM vm) {
        while (!items.isEmpty()) {
            popFront().emit(this, vm);
        }
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
