package codr7.shi;

public record Call(Call parent, Sloc sloc, ScriptMethod target, IValue[] registers, int returnPc) {
}
