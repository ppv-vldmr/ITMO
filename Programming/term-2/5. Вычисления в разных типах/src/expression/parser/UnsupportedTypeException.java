package expression.parser;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class UnsupportedTypeException extends Exception {
    public UnsupportedTypeException(String message) {
        super("unsupported type" + message);
    }
}
