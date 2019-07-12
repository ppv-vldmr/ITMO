package expression.token.variable;


import expression.TripleExpression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Variable implements TripleExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        return 0;
    }
}
