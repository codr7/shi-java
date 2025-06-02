package codr7.shi;

public interface Operation {
    Eval compile(VM vm, int pc);

    interface Eval {
        int eval(Values stack);
    }
}
