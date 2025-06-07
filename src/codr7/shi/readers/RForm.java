package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Input;
import codr7.shi.Reader;
import codr7.shi.VM;

public class RForm implements Reader {
    public static final RForm INSTANCE = new RForm();

    public boolean read(final VM vm, final Input in, final Forms out) {
        RSpace.INSTANCE.read(vm, in, out);
        final var c = in.get();
        in.unget();

        return switch (c) {
            case -1 -> false;
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> RInt.INSTANCE.read(vm, in, out);
            case '(' -> RList.INSTANCE.read(vm, in, out);
            default -> RId.INSTANCE.read(vm, in, out);
        };
    }
}
