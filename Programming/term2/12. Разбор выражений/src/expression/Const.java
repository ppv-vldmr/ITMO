package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Const implements CommonExpression {
    private double doubleValue;
    private int intValue;
    private final boolean isDouble;

    public Const(double value) {
        this.doubleValue = value;
        isDouble = true;
    }

    public Const(int value) {
        this.intValue = value;
        isDouble = false;
    }
    
    @Override
    public int evaluate(int x) {
        return evaluate(x, 0, 0);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return isDouble ? (int)doubleValue : intValue;
    }

    @Override
    public double evaluate(double x) {
        return isDouble ? doubleValue : intValue;
    }

    @Override
    public String toString() {
        return isDouble ? Double.toString(doubleValue) : Integer.toString(intValue);
    }

    @Override
    public int hashCode() {
        return isDouble ? (int)doubleValue : intValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            Const other = (Const)obj;
            if (this.isDouble == other.isDouble) {
              return (isDouble) ? (this.doubleValue == other.doubleValue) : (this.intValue == other.intValue);
            } else {
                return false;
            }
        }
        return false;
    }
}
