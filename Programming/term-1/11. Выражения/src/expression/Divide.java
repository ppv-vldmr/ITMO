package expression;

public class Divide extends BinaryOperation {

    public Divide(CompositeExpression par1, CompositeExpression par2) {
        super(par1, par2);
    }

    @Override
    char getOperation() {
        return '/';
    }

    @Override
    public int evaluate(int x, int y) {
        return x / y;
    }

    @Override
    public double evaluate(double x, double y) {
        return x / y;
    }
}
