package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
import java.util.Objects;

public class Variable implements CommonExpression {
    private String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Variable other = (Variable) obj;
            return Objects.equals(value, other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (value.equals("x")) {
            return x;
        } else if (value.equals("y")) {
            return y;
        } else if (value.equals("z")) {
            return z;
        } else {
            throw new IllegalArgumentException("Illegal variable " + value);
        }
    }

    @Override
    public double evaluate(double x) {
        return x;
    }
}
