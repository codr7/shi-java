package codr7.shi.operations;

import codr7.shi.*;
import codr7.shi.libraries.LCore;

import java.time.Duration;

public final class OBenchmark implements Operation {
    private final Label end;
    private final long rounds;

    public OBenchmark(final long rounds, final Label end) {
        this.rounds = rounds;
        this.end = end;
    }

    @Override
    public Evaluate compile(final VM vm, final int pc) {
        return (final Values stack, final IValue[] registers) -> {
            final var bodyStack = new Values();
            final var startTime = System.nanoTime();

            for (var i = 0; i < rounds; i++) {
                bodyStack.clear();
                vm.evaluate(pc + 1, end.pc, bodyStack);
            }

            final var elapsed = Duration.ofNanos(System.nanoTime() - startTime);
            stack.push(LCore.Time, elapsed);
            return end.pc;
        };
    }
}
