package codr7.shi.libraries;

import codr7.shi.*;
import codr7.shi.libraries.core.TBool;
import codr7.shi.libraries.core.TInt;
import codr7.shi.libraries.core.TMeta;
import codr7.shi.libraries.core.TMethod;

public class Core extends Library {
    public static final TBool Bool = new TBool(Symbol.get("Bool"));
    public static final TInt Int = new TInt(Symbol.get("Int"));
    public static final TMeta Meta = new TMeta(Symbol.get("Meta"));
    public static final TMethod Method = new TMethod(Symbol.get("Method"));

    public Core(final Symbol name, final Library parent) {
        super(name, parent);

        bind(Bool);
        bind(Int);
        bind(Meta);
        bind(Method);

        bind(Symbol.get("T"), new Value<>(Bool, true));
        bind(Symbol.get("F"), new Value<>(Bool, false));

        bind(Symbol.get("+"),
                new Method.Arg[]{new Method.Arg("x", Int), new Method.Arg("y", Int)},
                (final Sloc sloc, final Values stack, final VM vm) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x + y);
                });

        bind(Symbol.get("-"),
                new Method.Arg[]{new Method.Arg("x", Int), new Method.Arg("y", Int)},
                (final Sloc sloc, final Values stack, final VM vm) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Int, x - y);
                });

        bind(Symbol.get("<"),
                new Method.Arg[]{new Method.Arg("x", Int), new Method.Arg("y", Int)},
                (final Sloc sloc, final Values stack, final VM vm) -> {
                    final var y = stack.pop().cast(Int);
                    final var x = stack.pop().cast(Int);
                    stack.push(Bool, x < y);
                });
    }
}
