package operation;

import parser.Expression;

public class Summation extends BinaryOperation {
    public Summation(Expression left, Expression right) {
        super("+", left, right);
    }
}
