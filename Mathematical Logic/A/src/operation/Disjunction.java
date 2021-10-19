package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Disjunction extends BinaryOperation {
    public Disjunction(Expression left, Expression right) {
        super("|", left, right);
    }
}
