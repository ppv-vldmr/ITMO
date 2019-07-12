package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class LeftShift extends AbstractOperation {
    public LeftShift(CommonExpression left, CommonExpression right) {
        super(left, right, "<<");
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }


    @Override
    public double evaluate(double x) {
        double res = left.evaluate(x);
        double counter = right.evaluate(x);
        for (int k = 0; k < counter; k++) {
            res *= 2;
        }
        return res;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) << right.evaluate(x, y, z);
    }
}