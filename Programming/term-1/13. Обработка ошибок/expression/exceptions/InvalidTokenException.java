package expression.exceptions;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class InvalidTokenException extends ParsingException {
    public InvalidTokenException(char message, int idx) {
        super(String.format("unknown token %s at position %d", message, idx + 1));
    }
    public InvalidTokenException(String message, int idx) {
        super(String.format("unknown token %s at position %d", message, idx + 1));
    }
}
