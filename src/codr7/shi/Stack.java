package codr7.shi;

import java.util.ArrayList;

public class Stack {
    private final ArrayList<IValue> items = new ArrayList<>();

    public IValue peek() {
        return items.getLast();
    }

    public IValue pop() {
        return items.removeLast();
    }

    public void push(final IValue value) {
        items.add(value);
    }
}
