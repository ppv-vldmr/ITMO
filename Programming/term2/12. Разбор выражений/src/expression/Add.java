package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Add extends AbstractOperation {
    public Add(CommonExpression left, CommonExpression right) {
        super(left, right, "+");
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public double evaluate(double x) {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) + right.evaluate(x, y, z);
    }
}
