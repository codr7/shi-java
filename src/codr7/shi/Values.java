package codr7.shi;

import java.util.ArrayList;

final public class Values {
    private final ArrayList<IValue> items = new ArrayList<>();

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
