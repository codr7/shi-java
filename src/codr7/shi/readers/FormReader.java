package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Input;
import codr7.shi.Reader;
import codr7.shi.VM;

public class FormReader implements Reader {
    public static final FormReader INSTANCE = new FormReader();

    public boolean read(final VM vm, final Input in, final Forms out) {
        SpaceReader.INSTANCE.read(vm, in, out);
        final var c = in.get();
        in.unget();

        return switch (c) {
            case -1 -> false;
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> IntReader.INSTANCE.read(vm, in, out);
            case '(' -> ScopeReader.INSTANCE.read(vm, in, out);
            default -> IdReader.INSTANCE.read(vm, in, out);
        };
    }
}
