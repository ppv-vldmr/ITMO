package expression;

public abstract class BinaryOperation implements CompositeExpression {

    private CompositeExpression par1;
    private CompositeExpression par2;

    public BinaryOperation(CompositeExpression par1, CompositeExpression par2) {
        this.par1 = par1;
        this.par2 = par2;
    }

    abstract char getOperation();

    @Override
    public String toString() {
        return "(" + par1 + ' ' + getOperation() + ' ' + par2 + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        BinaryOperation bo = (BinaryOperation) o;

        return bo.par1.equals(this.par1) && bo.par2.equals(this.par2);
    }

    public int evaluate(int x) {
        return evaluate(par1.evaluate(x), par2.evaluate(x));
    }

    public double evaluate(double x) {
        return evaluate(par1.evaluate(x), par2.evaluate(x));
    }

    protected abstract int evaluate(int x, int y);

    protected abstract double evaluate(double x, double y);

    @Override
    public int hashCode() {
        return 13 * par1.hashCode() + 17 * par2.hashCode() + 19 * getClass().hashCode();
    }
}
