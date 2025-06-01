package codr7.shi;

public interface Op {
    Eval compile(VM vm, int pc);

    interface Eval {
        int eval(Values stack);
    }
}
