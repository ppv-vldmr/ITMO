package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
import java.util.Objects;

public abstract class AbstractOperation implements CommonExpression {
    protected CommonExpression left;
    protected CommonExpression right;
    protected final String type;

    public AbstractOperation(CommonExpression left, CommonExpression right, String type) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('(');
        result.append(left.toString());
        result.append(" ").append(type).append(" ");
        result.append(right.toString());
        result.append(')');
        return result.toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            AbstractOperation other = (AbstractOperation) obj;
            return Objects.equals(this.left, other.left) && Objects.equals(this.right, other.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 424241 * left.hashCode() + 37 * this.type.hashCode() + 41 * right.hashCode();
    }
}