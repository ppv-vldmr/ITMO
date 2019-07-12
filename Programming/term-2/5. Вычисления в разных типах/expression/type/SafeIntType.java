package expression.type;

import expression.type.exception.DivideByZeroException;
import expression.type.exception.OverflowException;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class SafeIntType extends AbstractType<Integer> {
    public SafeIntType(Integer v) {
        super(v);
    }

    @Override
    protected Integer maximumImpl(Integer v) {
        if (value > v)
            return value;
        else
            return v;
    }

    @Override
    protected Integer minimumImpl(Integer v) {
        if (value > v)
            return v;
        else
            return value;
    }

    @Override
    protected Integer countImpl() {
        return Integer.bitCount(value);
    }

    public static Type<Integer> parse(String s) {
        return new SafeIntType(Integer.parseInt(s));
    }

    @Override
    protected Type<Integer> valueOf(Integer value) {
        return new SafeIntType(value);
    }

    @Override
    public Integer absImpl() {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("abs", value);
        }
        return Math.abs(value);
    }

    @Override
    public Integer modImpl(Integer v) {
        if (v == 0) {
            throw new DivideByZeroException(v);
        }
        return value % v;
    }

    public Integer addImpl(Integer v) throws ArithmeticException {
        if (v > 0 && Integer.MAX_VALUE - v < value ||
                v < 0 && Integer.MIN_VALUE - v > value) {
            throw new OverflowException(value, "+", v);
        }
        return value + v;
    }

    @Override
    public Integer subtractImpl(Integer v) {
        if (v < 0 && Integer.MAX_VALUE + v < value ||
                v > 0 && Integer.MIN_VALUE + v > value) {
            throw new OverflowException(value, "-", v);
        }
        return value - v;
    }

    @Override
    public Integer multiplyImpl(Integer v) {
        if (value > 0 && v > 0 && Integer.MAX_VALUE / v < value ||
                value > 0 && v < 0 && Integer.MIN_VALUE / value > v ||
                value < 0 && v > 0 && Integer.MIN_VALUE / v > value ||
                value < 0 && v < 0 && Integer.MAX_VALUE / v > value) {
            throw new OverflowException(value, "*", v);
        }
        return value * v;
    }

    @Override
    public Integer divideImpl(Integer v) {
        if (v == 0) {
            throw new DivideByZeroException(value);
        }
        if (value == Integer.MIN_VALUE && v == -1) {
            throw new OverflowException(value, "/", v);
        }
        return value / v;
    }

    @Override
    public Integer negateImpl() {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("-", value);
        }
        return -value;
    }

}
