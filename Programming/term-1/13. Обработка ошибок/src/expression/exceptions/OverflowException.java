package expression.exceptions;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class OverflowException extends ArithmeticException {
    public OverflowException(int left, String op, int right) {
        super(String.format("overflow %d %s %d", left, op, right));
    }

    public OverflowException(String op, int arg) {
        super(String.format("overflow %s%d", op, arg));
    }
}
