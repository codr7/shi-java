package codr7.shi;

import java.util.ArrayList;

final public class Values {
    private final ArrayList<IValue> items = new ArrayList<>();

    public IValue peek() {
        return items.getLast();
    }

    public IValue pop() {
        return items.removeLast();
    }

    public void push(final IValue it) {
        items.add(it);
    }
}
