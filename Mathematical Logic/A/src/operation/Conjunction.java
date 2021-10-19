package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Conjunction extends BinaryOperation {
    public Conjunction(Expression left, Expression right) {
        super("&", left, right);
    }
}
