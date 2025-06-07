package codr7.shi;

public record Call(Call parent, Sloc sloc, Method target, int returnPc) {
}
