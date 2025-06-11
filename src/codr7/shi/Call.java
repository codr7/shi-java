package codr7.shi;

public record Call(Call parent,
                   ShiMethod target,
                   Sloc sloc,
                   ICell[] argumentRegisters,
                   int returnPc) {
}
