package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Input;
import codr7.shi.Reader;
import codr7.shi.VM;

public class RWhitespace implements Reader {
    public static final RWhitespace INSTANCE = new RWhitespace();

    public boolean read(final VM vm, final Input in, final Forms out) {
        for (; ; ) {
            if (!Character.isWhitespace(in.get())) {
                in.unget();
                break;
            }
        }

        return true;
    }
}