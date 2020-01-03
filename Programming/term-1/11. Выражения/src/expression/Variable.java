package expression;

public class Variable implements CompositeExpression {

    private String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        return ((Variable) o).var.equals(this.var);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
