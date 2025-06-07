package codr7.shi;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Shell {
    private final BufferedReader in;
    private final PrintStream out;
    private final VM vm;

    public Shell(final VM vm, final InputStream in, final PrintStream out) {
        this.vm = vm;
        this.in = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        this.out = out;
    }

    public void run(final Values stack) {
        final var inputBuffer = new StringBuilder();
        final var sloc = new Sloc("repl");
        var lineIndex = 0;

        for (; ; ) {
            out.printf("% 2d ", sloc.line() + lineIndex);
            String line;

            try {
                line = in.readLine();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }

            if (line.isEmpty()) {
                try {
                    vm.evaluate(inputBuffer.toString(), stack, sloc);
                    stack.dump(vm, out);
                    out.println();
                    out.println();
                } catch (final Exception e) {
                    out.println(e.getMessage());
                } finally {
                    inputBuffer.setLength(0);
                    lineIndex = 0;
                }
            } else {
                inputBuffer.append(line);
                inputBuffer.append('\n');
                lineIndex++;
            }
        }
    }
}