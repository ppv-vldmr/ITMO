package kek.translation;

import java.util.ArrayList;
import java.util.List;

public final class KekOperator implements KekExpr {
    private final KekOperatorRule rule;
    private final KekExpr[] kekExprs;
    private final KekType[] argTypes;

    public KekOperator(KekOperatorRule rule, KekExpr... kekExprs) {
        this.rule = rule;
        this.kekExprs = kekExprs;
        this.argTypes = new KekType[kekExprs.length];
        for (int i = 0; i < kekExprs.length; ++i) {
            this.argTypes[i] = kekExprs[i].getType();
        }

        if (!rule.isApplicable(argTypes)) {
            throw new RuntimeException();
        }
    }

    @Override
    public KekType getType() {
        return rule.getResultType(argTypes);
    }

    @Override
    public String getCString() {
        return switch (rule.getArity()) {
            case 1 -> get1ArityC();
            case 2 -> get2ArityC();
            default -> throw new RuntimeException();
        };
    }

    private String get1ArityC() {
        if (rule.isRealOperator(argTypes)) {
            return "(" + rule.getCString(argTypes) + "(" + kekExprs[0].getCString() + "))";
        } else {
            return rule.getCString(argTypes) + "(" + kekExprs[0].getCString() + ")";
        }
    }

    private String get2ArityC() {
        if (rule.isRealOperator(argTypes)) {
            return "(" + kekExprs[0].getCString() + " " + rule.getCString(argTypes) + " " + kekExprs[1].getCString() + ")";
        } else {
            return rule.getCString(argTypes) + "(" + kekExprs[0].getCString() + ", " + kekExprs[1].getCString() + ")";
        }
    }

    public static KekExpr fold(List<KekOperatorRule> rules, List<KekExpr> kekExprs) {
        if (rules.size() + 1 != kekExprs.size())
            throw new RuntimeException();
        if (kekExprs.size() == 1)
            return kekExprs.get(0);
        for (KekOperatorRule rule : rules) {
            if (rule.getArity() != 2)
                throw new RuntimeException();
        }
        return fold2Arity(rules, kekExprs);
    }

    private static <T> List<T> dropFirst(List<T> list) {
        return dropFirstN(list, 1);
    }

    private static <T> List<T> dropFirstN(List<T> list, int n) {
        return list.subList(n, list.size());
    }

    private static KekOperator fold2Arity(List<KekOperatorRule> rules, List<KekExpr> kekExprs) {
        if (kekExprs.size() == 2)
            return new KekOperator(rules.get(0), kekExprs.get(0), kekExprs.get(1));
        return switch (rules.get(0).getAssociativity()) {
            case LEFT -> {
                KekOperator left = new KekOperator(rules.get(0), kekExprs.get(0), kekExprs.get(1));
                List<KekExpr> args = new ArrayList<>(dropFirstN(kekExprs, 2));
                args.add(0, left);
                yield fold2Arity(dropFirst(rules), args);
            }
            case RIGHT -> new KekOperator(rules.get(0), kekExprs.get(0), fold2Arity(dropFirst(rules), dropFirst(kekExprs)));
            case NONE -> throw new RuntimeException();
        };
    }
}
