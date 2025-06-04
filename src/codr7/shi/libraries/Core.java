package codr7.shi.libraries;

import codr7.shi.Library;
import codr7.shi.Symbol;
import codr7.shi.VM;
import codr7.shi.Value;
import codr7.shi.libraries.core.Bool;
import codr7.shi.libraries.core.Int;
import codr7.shi.libraries.core.Meta;
import codr7.shi.libraries.core.Method;

public class Core extends Library {
    public static final Bool Bool = new Bool(Symbol.get("Bool"));
    public static final Int Int = new Int(Symbol.get("Int"));
    public static final Meta Meta = new Meta(Symbol.get("Meta"));
    public static final Method Method = new Method(Symbol.get("Method"));

    public Core(final Symbol name, final Library parent) {
        super(name, parent);

        bind(Bool);
        bind(Int);
        bind(Meta);
        bind(Method);

        bind(Symbol.get("T"), new Value<>(Bool, true));
        bind(Symbol.get("F"), new Value<>(Bool, false));
    }
}
