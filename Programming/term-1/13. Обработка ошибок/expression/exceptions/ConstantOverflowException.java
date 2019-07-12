package expression.exceptions;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class ConstantOverflowException extends ParsingException {
    public ConstantOverflowException(String message, int idx) {
        super(String.format("constant overflow %s at position %d",message, idx + 1));
    }
}
