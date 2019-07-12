package expression.type;


import java.math.BigInteger;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class DoubleType extends AbstractType<Double> {
    public DoubleType(Double value) {
        super(value);
    }

    @Override
    protected Double maximumImpl(Double v) {
        if (value < v)
            return v;
        else
            return value;
    }

    @Override
    protected Double minimumImpl(Double v) {
        if (value < v)
            return value;
        else
            return v;
    }

    @Override
    protected Double countImpl() {
        String bin = Long.toBinaryString(Double.doubleToLongBits(value));
        Double cnt = 0.0;
        for (int i = 0; i < bin.length(); i++)
            if (bin.charAt(i) == '1')
                cnt++;
        return cnt;
    }

    public static DoubleType parse(String s) {
        return new DoubleType(Double.parseDouble(s));
    }

    @Override
    protected Type<Double> valueOf(Double value) {
        return new DoubleType(value);
    }

    @Override
    public Double absImpl() {
        return Math.abs(value);
    }

    @Override
    public Double modImpl(Double v) {
        return value % v;
    }

    @Override
    public Double addImpl(Double v) {
        return value + v;
    }

    @Override
    public Double subtractImpl(Double v) {
        return value - v;
    }

    @Override
    public Double multiplyImpl(Double v) {
        return value * v;
    }

    @Override
    public Double divideImpl(Double v) {
        return value / v;
    }

    @Override
    public Double negateImpl() {
        return -value;
    }
}
