package codr7.shi.libraries;

import codr7.shi.*;
import codr7.shi.forms.FId;
import codr7.shi.libraries.core.*;
import codr7.shi.operations.OBranch;
import codr7.shi.operations.OGoto;

public class LCore extends Library {
    public static final TBool Bool = new TBool(Symbol.get("Bool"));
    public static final TInt Int = new TInt(Symbol.get("Int"));
    public static final TMacro Macro = new TMacro(Symbol.get("Macro"));
    public static final TMeta Meta = new TMeta(Symbol.get("Meta"));
    public static final TMethod Method = new TMethod(Symbol.get("Method"));

    public LCore() {
        super(Symbol.get("core"), null);

        bind(Bool);
        bind(Int);
        bind(Macro);
        bind(Meta);
        bind(Method);

        bind(Symbol.get("T"), new Value<>(Bool, true));
        bind(Symbol.get("F"), new Value<>(Bool, false));

        bindMethod(Symbol.get("+"),
                new Method.Arg[]{new Method.Arg("x", Int), new Method.Arg("y", Int)},
                (final VM vm, final Values stack, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x + y);
                });

        bindMethod(Symbol.get("-"),
                new Method.Arg[]{new Method.Arg("x", Int), new Method.Arg("y", Int)},
                (final VM vm, final Values stack, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x - y);
                });

        bindMethod(Symbol.get("<"),
                new Method.Arg[]{new Method.Arg("x", Int), new Method.Arg("y", Int)},
                (final VM vm, final Values stack, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Bool, x < y);
                });

        bindMacro(Symbol.get("if"),
                new String[]{"cond", "left"},
                (final VM vm, final Forms in, final Sloc sloc) -> {
                    in.popFront().emit(in, vm);
                    final var end = new Label();
                    vm.emit(new OBranch(end));
                    in.popFront().emit(in, vm);

                    if (in.length() > 0 && in.peekFront() instanceof FId id && id.name == Symbol.get("else")) {
                        in.popFront();
                        final var elseEnd = new Label();
                        vm.emit(new OGoto(elseEnd));
                        end.pc = vm.emitPc();
                        in.popFront().emit(in, vm);
                        elseEnd.pc = vm.emitPc();
                    } else {
                        end.pc = vm.emitPc();
                    }
                });
    }
}
