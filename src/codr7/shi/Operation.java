package codr7.shi;

public interface Operation {
    Evaluate compile(VM vm, int pc);

    interface Evaluate {
        int eval(Cells stack, IValue[] registers);
    }
}
