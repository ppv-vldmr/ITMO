package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Implication extends BinaryOperation {
    public Implication(Expression left, Expression right) {
        super("->", left, right);
    }
}
