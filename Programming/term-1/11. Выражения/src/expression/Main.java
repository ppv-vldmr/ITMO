package expression;

public class Main {

    public static void main(String[] args) {
        CompositeExpression expr = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")), new Multiply(new Const(2.0), new Variable("x"))), new Const(1.0));
        System.out.println(expr.evaluate(Double.parseDouble(args[0])));
    }
}
