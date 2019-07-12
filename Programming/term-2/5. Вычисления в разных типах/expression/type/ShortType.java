package expression.type;

import java.math.BigInteger;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class ShortType extends AbstractType<Short> {
    public ShortType(Short value) {
        super(value);
    }

    public static Type<Short> parse(String s) {
        return new ShortType((short) Integer.parseInt(s));
    }

    @Override
    protected Short maximumImpl(Short v) {
        return value > v ? value : v;
    }

    @Override
    protected Short minimumImpl(Short v) {
        return value < v ? value : v;
    }

    @Override
    protected Short countImpl() {
        return null;
    }

    @Override
    protected Short negateImpl() {
        return (short) -value;
    }

    @Override
    protected Short absImpl() {
        return value > 0 ? value : (short) -value;
    }

    @Override
    protected Short addImpl(Short v) {
        return (short) (value + v);
    }

    @Override
    protected Short subtractImpl(Short v) {
        return (short) (value - v);
    }

    @Override
    protected Short multiplyImpl(Short v) {
        return (short) (value * v);
    }

    @Override
    protected Short divideImpl(Short v) {
        return (short) (value / v);
    }

    @Override
    protected Short modImpl(Short v) {
        return (short) (value % v);
    }

    @Override
    protected Type<Short> valueOf(Short value) {
        return new ShortType(value);
    }
}
