package expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {
    boolean equals(Object obj);
    String toString();
    int hashCode();
}
