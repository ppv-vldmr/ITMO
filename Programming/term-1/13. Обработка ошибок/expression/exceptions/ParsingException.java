package expression.exceptions;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class ParsingException extends Exception {
    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message,int idx) {
        super(String.format("%s at position %d",message,idx + 1));
    }
}
