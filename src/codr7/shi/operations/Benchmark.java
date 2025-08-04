package codr7.shi.operations;

import codr7.shi.*;
import codr7.shi.libraries.Core;

import java.time.Duration;

public final class Benchmark implements Operation {
    private final Label end;
    private final long rounds;

    public Benchmark(final long rounds, final Label end) {
        this.rounds = rounds;
        this.end = end;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Cells stack, final ICell[] registers) -> {
            final var bodyStack = new Cells();
            final var startTime = System.nanoTime();

            for (var i = 0; i < rounds; i++) {
                bodyStack.clear();
                vm.evaluate(pc + 1, end.pc, bodyStack);
            }

            stack.push(Core.Int, System.nanoTime() - startTime);
            return end.pc;
        };
    }
}
