package expression.type;

import java.math.BigInteger;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class LongType extends AbstractType<Long> {
    public LongType(Long value) {
        super(value);
    }

    public static Type<Long> parse(String s) {
        return new LongType(new Long(s));
    }

    @Override
    protected Long maximumImpl(Long v) {
        if (value > v)
            return value;
        else
            return v;
    }

    @Override
    protected Long minimumImpl(Long v) {
        if (value < v)
            return value;
        else
            return value;
    }

    @Override
    protected Long countImpl() {
        return null;
    }

    @Override
    protected Long negateImpl() {
        return -value;
    }

    @Override
    protected Long absImpl() {
        if (value < 0)
            return -value;
        else
            return value;
    }

    @Override
    protected Long addImpl(Long v) {
        return value + v;
    }

    @Override
    protected Long subtractImpl(Long v) {
        return value - v;
    }

    @Override
    protected Long multiplyImpl(Long v) {
        return value * v;
    }

    @Override
    protected Long divideImpl(Long v) {
        return value / v;
    }

    @Override
    protected Long modImpl(Long v) {
        return value % v;
    }

    @Override
    protected Type<Long> valueOf(Long value) {
        return  new LongType(value);
    }
}
