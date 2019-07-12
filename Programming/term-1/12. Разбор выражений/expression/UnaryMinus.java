package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class UnaryMinus extends AbstractOperation {
    public UnaryMinus(CommonExpression value) {
        super(value, value, "-");
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(type).append('(');
        toReturn.append(left.toString());
        toReturn.append(')');
        return toReturn.toString();
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public double evaluate(double x) {
        return -x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -left.evaluate(x, y, z);
    }
}
