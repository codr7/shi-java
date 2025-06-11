package codr7.shi.libraries;

import codr7.shi.*;
import codr7.shi.forms.FId;
import codr7.shi.forms.FList;
import codr7.shi.libraries.core.*;
import codr7.shi.operations.*;

public class LCore extends Library {
    public static final ICellType Any = new Trait("Any");
    public static final TBinding Binding = new TBinding("Binding", Any);
    public static final TBool Bool = new TBool("Bool", Any);
    public static final TInt Int = new TInt("Int", Any);
    public static final TMacro Macro = new TMacro("Macro", Any);
    public static final TMeta Meta = new TMeta("Meta", Any);
    public static final TMethod Method = new TMethod("Method", Any);
    public static final TTime Time = new TTime("Time", Any);

    public LCore() {
        super("core", null);

        bind(Binding);
        bind(Bool);
        bind(Int);
        bind(Macro);
        bind(Meta);
        bind(Method);

        bind("T", new Cell<>(Bool, true));
        bind("F", new Cell<>(Bool, false));

        bindMethod("+",
                new Method.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x + y);
                });

        bindMethod("-",
                new Method.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x - y);
                });

        bindMethod("*",
                new Method.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x * y);
                });

        bindMethod("=",
                new Method.Arguments()
                        .add("x", Any)
                        .add("y", Any),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    final var y = stack.pop();
                    final var x = stack.pop();
                    stack.push(Bool, x.equals(y));
                });

        bindMethod("<",
                new Method.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Bool, x < y);
                });

        bindMethod(">",
                new Method.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Bool, x > y);
                });

        bindMacro("benchmark",
                new Macro.Arguments()
                        .add("rounds")
                        .add("body"),
                (final VM vm, final Forms in, final Sloc sloc) -> {
                    final var rounds = in.popFront().value(vm, Int);
                    final var end = new Label();
                    vm.emit(new OBenchmark(rounds, end));
                    in.popFront().emit(vm, in);
                    end.pc = vm.emitPc();
                });

        bindMacro("check",
                new Macro.Arguments()
                        .add("expected")
                        .add("actual"),
                (final VM vm, final Forms in, final Sloc sloc) -> {
                    final var expected = in.popFront().value(vm);
                    in.popFront().emit(vm, in);
                    vm.emit(new OCheck(expected, sloc));
                });

        bindMacro("if",
                new Macro.Arguments()
                        .add("cond")
                        .add("left"),
                (final VM vm, final Forms in, final Sloc sloc) -> {
                    in.popFront().emit(vm, in);
                    final var end = new Label();
                    vm.emit(new OBranch(end));
                    in.popFront().emit(vm, in);

                    if (in.length() > 0 && in.peekFront() instanceof FId id && id.name == Symbol.get("else")) {
                        in.popFront();
                        final var elseEnd = new Label();
                        vm.emit(new OGoto(elseEnd));
                        end.pc = vm.emitPc();
                        in.popFront().emit(vm, in);
                        elseEnd.pc = vm.emitPc();
                    } else {
                        end.pc = vm.emitPc();
                    }
                });

        bindMacro("method",
                new Macro.Arguments()
                        .add("name")
                        .add("args")
                        .add("body"),
                (final VM vm, final Forms in, final Sloc sloc) -> {
                    final var name = in.popFront().cast(FId.class).name;
                    final var argForms = in.popFront().cast(FList.class).body;
                    final var args = new Method.Arguments();

                    for (var i = 0; i < argForms.length; i++) {
                        final var argName = argForms[i].cast(FId.class).name;
                        var argType = (argForms.length > i + 1) ? argForms[i + 1].value(vm, LCore.Meta) : null;

                        if (argType == null) {
                            argType = Any;
                        } else {
                            i++;
                        }

                        args.add(argName, argType);
                    }

                    final var end = new Label();
                    vm.emit(new OGoto(end));
                    final var rArgs = vm.allocate(args.length());
                    final var m = new ShiMethod(name, args, rArgs, vm.emitPc());
                    vm.currentLibrary().bind(name, new Cell<>(LCore.Method, m));

                    vm.withLibrary(null, () -> {
                        for (var i = 0; i < m.arguments.length; i++) {
                            vm.currentLibrary().bind(
                                    m.arguments[m.arguments.length - i - 1].name(),
                                    new Cell<>(Binding, rArgs + i));
                        }

                        vm.emit(new OPut(rArgs, m.arguments.length));
                        in.popFront().emit(vm, in);
                    });

                    vm.emit(new OReturn());
                    end.pc = vm.emitPc();
                });

        bindMethod("say",
                new Method.Arguments()
                        .add("what", Any),
                (final VM vm, final Cells stack, final IValue[] registers, final Sloc sloc) -> {
                    stack.pop().write(vm, System.out);
                    System.out.println();
                });

    }
}
