package codr7.shi.readers;

import codr7.shi.Forms;
import codr7.shi.Reader;
import codr7.shi.Sloc;
import codr7.shi.VM;

import java.io.PushbackReader;

public class FormReader implements Reader {
    public static final FormReader INSTANCE = new FormReader();

    public boolean read(final VM vm, final PushbackReader in, final Forms out, final Sloc sloc) {
        return false;
    }
}
