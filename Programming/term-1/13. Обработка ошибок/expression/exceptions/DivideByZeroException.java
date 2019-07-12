package expression.exceptions;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class DivideByZeroException extends ArithmeticException {
    public DivideByZeroException(int left) {
        super(String.format("division by zero %d / 0", left));
    }
}
