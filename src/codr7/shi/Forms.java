package codr7.shi;

import java.util.ArrayDeque;

final public class Forms {
    private final ArrayDeque<Form> items = new ArrayDeque<>();

    public Form peekFront() {
        return items.getFirst();
    }

    public Form popFront() {
        return items.removeFirst();
    }

    public void pushBack(final Form it) {
        items.addLast(it);
    }
}
