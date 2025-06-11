package codr7.shi;

public record Call(Call parent, Sloc sloc, ShiMethod target, IValue[] registers, int returnPc) {
}
