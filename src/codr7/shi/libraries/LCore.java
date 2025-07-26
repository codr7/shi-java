package codr7.shi.libraries;

import codr7.shi.*;
import codr7.shi.forms.FIdentifier;
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

    public LCore(final VM vm) {
        super(vm, "core", null);

        bind(Binding);
        bind(Bool);
        bind(Int);
        bind(Macro);
        bind(Meta);
        bind(Method);

        bind("T", new Cell<>(Bool, true));
        bind("F", new Cell<>(Bool, false));

        bindMethod("+",
                new BaseMethod.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x + y);
                });

        bindMethod("-",
                new BaseMethod.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x - y);
                });

        bindMethod("*",
                new BaseMethod.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x * y);
                });

        bindMethod("=",
                new BaseMethod.Arguments()
                        .add("x", Any)
                        .add("y", Any),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    final var y = stack.pop();
                    final var x = stack.pop();
                    stack.push(Bool, x.equals(y));
                });

        bindMethod("<",
                new BaseMethod.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Bool, x < y);
                });

        bindMethod(">",
                new BaseMethod.Arguments()
                        .add("x", Int)
                        .add("y", Int),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Bool, x > y);
                });

        bindMacro("benchmark",
                new BaseMacro.Arguments()
                        .add("rounds")
                        .add("body"),
                (final Forms in, final Sloc sloc) -> {
                    final var rounds = in.popFront().value(vm, Int);
                    final var end = new Label();
                    vm.emit(new Benchmark(rounds, end));
                    in.popFront().emit(vm, in);
                    end.pc = vm.emitPc();
                });

        bindMacro("check",
                new BaseMacro.Arguments()
                        .add("expected")
                        .add("actual"),
                (final Forms in, final Sloc sloc) -> {
                    final var expected = in.popFront().value(vm);
                    in.popFront().emit(vm, in);
                    vm.emit(new CheckValue(expected, sloc));
                });

        bindMacro("if",
                new BaseMacro.Arguments()
                        .add("cond")
                        .add("left"),
                (final Forms in, final Sloc sloc) -> {
                    in.popFront().emit(vm, in);
                    final var end = new Label();
                    vm.emit(new Branch(end));
                    in.popFront().emit(vm, in);

                    if (in.length() > 0 && in.peekFront() instanceof FIdentifier id && id.name == Symbol.get("else")) {
                        in.popFront();
                        final var elseEnd = new Label();
                        vm.emit(new Goto(elseEnd));
                        end.pc = vm.emitPc();
                        in.popFront().emit(vm, in);
                        elseEnd.pc = vm.emitPc();
                    } else {
                        end.pc = vm.emitPc();
                    }
                });

        bindMacro("method",
                new BaseMacro.Arguments()
                        .add("name")
                        .add("args")
                        .add("body"),
                (final Forms in, final Sloc sloc) -> {
                    final var name = in.popFront().cast(FIdentifier.class).name;
                    final var argForms = in.popFront().cast(FList.class).body;
                    final var args = new BaseMethod.Arguments();

                    for (var i = 0; i < argForms.length; i++) {
                        final var argName = argForms[i].cast(FIdentifier.class).name;
                        var argType = (argForms.length > i + 1) ? argForms[i + 1].value(vm, LCore.Meta) : null;

                        if (argType == null) {
                            argType = Any;
                        } else {
                            i++;
                        }

                        args.add(argName, argType);
                    }

                    final var end = new Label();
                    vm.emit(new Goto(end));
                    final var rArgs = vm.allocateRegisters(args.length());
                    final var m = new ShiMethod(vm, name, args, rArgs, vm.emitPc());
                    vm.library().bind(name, new Cell<>(LCore.Method, m));

                    vm.libraryDo(() -> {
                        for (var i = 0; i < m.arguments.length; i++) {
                            vm.library().bind(
                                    m.arguments[m.arguments.length - i - 1].name(),
                                    new Cell<>(Binding, rArgs + i));
                        }

                        vm.emit(new PutRegister(rArgs, m.arguments.length));
                        in.popFront().emit(vm, in);
                    });

                    vm.emit(new Return());
                    end.pc = vm.emitPc();
                });

        bindMethod("say",
                new BaseMethod.Arguments()
                        .add("what", Any),
                (final Cells stack, final ICell[] registers, final Sloc sloc) -> {
                    stack.pop().dump(vm, System.out);
                    System.out.println();
                });

    }
}
