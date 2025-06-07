package codr7.shi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public interface Dumper {
    void dump(final VM vm, final PrintStream out);

    default String dump(final VM vm) {
        try {
            try (final var s = new ByteArrayOutputStream(); final var ps = new PrintStream(s)) {
                dump(vm, ps);
                return s.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
