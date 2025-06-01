package codr7.shi;

import java.io.PushbackReader;

public interface Reader {
    boolean read(VM vm, PushbackReader in, Forms out, Sloc sloc);
}