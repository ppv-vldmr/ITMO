package operation;

import parser.Expression;

public class Multiplication extends BinaryOperation {
    public Multiplication(Expression left, Expression right) {
        super("*", left, right);
    }
}
