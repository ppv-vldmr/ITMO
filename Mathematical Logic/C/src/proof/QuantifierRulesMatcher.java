package proof;

import java.util.List;

import operation.Implication;
import operation.WithQuantifier;
import parser.Expression;

public class QuantifierRulesMatcher {
    public int isRuleWithQuantifierAny(Expression expression, List<Expression> steps) {
        try {
            Implication whole = (Implication) expression;
            WithQuantifier quan = (WithQuantifier) whole.getRight();
            if (!quan.getQuantifier().equals("@")) {
                return -1;
            }
            if (!quan.getExpr().isFree(quan.getVar().getName())) {
                Expression compareWith = new Implication(whole.getLeft(), quan.getExpr());
                for (int i = 0; i < steps.size(); i++) {
//                    System.out.println(steps.get(i).toStringInfix());
                    if (steps.get(i).equals(compareWith)) {
                        return i + 1;
                    }
                }
            } else {
                System.out.println("Expression " + (steps.size() + 1) + ": variable " + quan.getVar().toStringInfix() + " occurs free in @-rule.");
                System.exit(0);
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int isRuleWithQuantifierExists(Expression expression, List<Expression> steps) {
        try {
            Implication whole = (Implication) expression;
            WithQuantifier quan = (WithQuantifier) whole.getLeft();
            if (!quan.getQuantifier().equals("?")) {
                return -1;
            }
            if (!quan.getExpr().isFree(quan.getVar().getName())) {
                Expression compareWith = new Implication(quan.getExpr(), whole.getRight());
                for (int i = 0; i < steps.size(); i++) {
                    if (steps.get(i).equals(compareWith)) {
                        return i + 1;
                    }
                }
            } else {
                System.out.println("Expression " + (steps.size() + 1) + ": variable " + quan.getVar().toStringInfix() + " occurs free in ?-rule.");
                System.exit(0);
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }
}
