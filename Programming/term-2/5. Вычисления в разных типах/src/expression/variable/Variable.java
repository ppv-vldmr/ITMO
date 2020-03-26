package expression.variable;


import expression.TripleExpression;
import expression.type.Type;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Variable<T extends Number> implements TripleExpression<T> {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Type<T> evaluate(Type<T> x, Type<T> y, Type<T> z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        return null;
    }
}
