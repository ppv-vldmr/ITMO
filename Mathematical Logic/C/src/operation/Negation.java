package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Negation extends UnaryOperation {
    public Negation(Expression left) {
        super("!", left);
    }
}
