package operation;

import parser.Expression;

public class Increment extends UnaryOperation {
    public Increment(Expression val) {
        super("'", val);
    }
}
