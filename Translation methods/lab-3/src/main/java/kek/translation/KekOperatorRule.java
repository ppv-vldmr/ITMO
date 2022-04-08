package kek.translation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kek.translation.KekPrimitiveType.BOOL;
import static kek.translation.KekPrimitiveType.INT;

public enum KekOperatorRule {
    NOT(Associativity.NONE, 1, v("~", INT, INT), v("~", BOOL, BOOL)),
    MUL(Associativity.LEFT, 2,
            v("*", INT, INT, INT)//,
//            f("kek_ai_mul", array(INT), array(INT), INT),
//            f("kek_ab_mul", array(BOOL), array(BOOL), INT),
//            f("kek_aai_mul", array(array(INT)), array(array(INT)), INT),
//            f("kek_aab_mul", array(array(BOOL)), array(array(BOOL)), INT),
//            f("kek_aaai_mul", array(array(array(INT))), array(array(array(INT))), INT),
//            f("kek_aaab_mul", array(array(array(BOOL))), array(array(array(BOOL))), INT)
    ),
    DIV(Associativity.LEFT, 2, v("/", INT, INT, INT)),
    MOD(Associativity.LEFT, 2, v("%", INT, INT, INT)),
    PLUS(Associativity.LEFT, 2, v("+", INT, INT, INT)),
    MINUS(Associativity.LEFT, 2, v("-", INT, INT, INT)),
    EQUALS(Associativity.LEFT, 2, v("==", BOOL, INT, INT), v("==", BOOL, BOOL, BOOL)),
    NOT_EQUALS(Associativity.LEFT, 2, v("!=", BOOL, INT, INT), v("!=", BOOL, BOOL, BOOL)),
    OR(Associativity.LEFT, 2, v("|", INT, INT, INT), v("||", BOOL, BOOL, BOOL)),
    AND(Associativity.LEFT, 2, v("&", INT, INT, INT), v("&&", BOOL, BOOL, BOOL)),
    INDEX(Associativity.LEFT, 2,
            f("kek_get_array", INT, array(INT), INT),
            f("kek_get_array", BOOL, array(BOOL), INT),
            f("kek_get_array", array(INT), array(array(INT)), INT),
            f("kek_get_array", array(BOOL), array(array(BOOL)), INT),
            f("kek_get_array", array(array(INT)), array(array(array(INT))), INT),
            f("kek_get_array", array(array(BOOL)), array(array(array(BOOL))), INT)
    );

    private final Associativity associativity;
    private final int arity;
    private final Map<List<KekType>, KekType> argsToResultType = new HashMap<>();
    private final Map<List<KekType>, String> argsToC = new HashMap<>();
    private final Map<List<KekType>, RuleType> argsToRuleType = new HashMap<>();

    KekOperatorRule(Associativity associativity, int arity, KekOperatorRuleHolder... variants) {
        this.associativity = associativity;
        this.arity = arity;
        for (KekOperatorRuleHolder variant : variants) {
            assert variant.args().length == arity;
            List<KekType> args = List.of(variant.args());
            argsToC.put(args, variant.c());
            argsToResultType.put(args, variant.resultType());
            argsToRuleType.put(args, variant.ruleType());
        }
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    public int getArity() {
        return arity;
    }

    public KekType getResultType(KekType... types) {
        return argsToResultType.get(List.of(types));
    }

    public boolean isApplicable(KekType... types) {
        return argsToResultType.containsKey(List.of(types));
    }

    public String getCString(KekType... types) {
        return argsToC.get(List.of(types));
    }

    public boolean isRealOperator(KekType... types) {
        return argsToRuleType.get(List.of(types)) == RuleType.OPERATOR;
    }

    private static KekOperatorRuleHolder v(String c, KekType resultType, KekType... args) {
        return new KekOperatorRuleHolder(RuleType.OPERATOR, c, resultType, args);
    }

    private static KekOperatorRuleHolder f(String funcName, KekType resultType, KekType... args) {
        return new KekOperatorRuleHolder(RuleType.FUNCTION, funcName, resultType, args);
    }

    private static KekArrayType array(KekType innerType) {
        return new KekArrayType(innerType);
    }

    private record KekOperatorRuleHolder(RuleType ruleType, String c, KekType resultType, KekType... args) {
    }

    public enum Associativity {
        NONE, LEFT, RIGHT;
    }

    private enum RuleType {
        OPERATOR, FUNCTION;
    }
}
