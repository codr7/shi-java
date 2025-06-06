package codr7.shi.libraries;

import codr7.shi.*;
import codr7.shi.libraries.core.BoolType;
import codr7.shi.libraries.core.IntType;
import codr7.shi.libraries.core.MetaType;
import codr7.shi.libraries.core.MethodType;

public class Core extends Library {
    public static final BoolType BOOL = new BoolType(Symbol.get("Bool"));
    public static final IntType INT = new IntType(Symbol.get("Int"));
    public static final MetaType META = new MetaType(Symbol.get("Meta"));
    public static final MethodType METHOD = new MethodType(Symbol.get("Method"));

    public Core(final Symbol name, final Library parent) {
        super(name, parent);

        bind(BOOL);
        bind(INT);
        bind(META);
        bind(METHOD);

        bind(Symbol.get("T"), new Value<>(BOOL, true));
        bind(Symbol.get("F"), new Value<>(BOOL, false));

        bind(Symbol.get("+"),
                new Method.Arg[]{new Method.Arg("x", INT), new Method.Arg("y", INT)},
                (final Sloc sloc, final Values stack, final VM vm) -> {
                    final var y = stack.pop().cast(INT);
                    final var x = stack.pop().cast(INT);
                    stack.push(INT, x + y);
                });

        bind(Symbol.get("-"),
                new Method.Arg[]{new Method.Arg("x", INT), new Method.Arg("y", INT)},
                (final Sloc sloc, final Values stack, final VM vm) -> {
                    final var y = stack.pop().cast(INT);
                    final var x = stack.pop().cast(INT);
                    stack.push(INT, x - y);
                });

        bind(Symbol.get("<"),
                new Method.Arg[]{new Method.Arg("x", INT), new Method.Arg("y", INT)},
                (final Sloc sloc, final Values stack, final VM vm) -> {
                    final var y = stack.pop().cast(INT);
                    final var x = stack.pop().cast(INT);
                    stack.push(BOOL, x < y);
                });
    }
}
