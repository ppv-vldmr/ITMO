package expression.type;

import expression.TripleExpression;

import java.math.BigInteger;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class BigIntType extends AbstractType<BigInteger> {
    public BigIntType(BigInteger value) {
        super(value);
    }

    @Override
    protected BigInteger maximumImpl(BigInteger v) {
        return value.max(v);
    }

    @Override
    protected BigInteger minimumImpl(BigInteger v) {
        return value.min(v);
    }

    @Override
    protected BigInteger countImpl() {
        return BigInteger.valueOf(value.bitCount());
    }

    public static Type<BigInteger> parse(String s) {
        return new BigIntType(new BigInteger(s));
    }

    @Override
    protected Type<BigInteger> valueOf(BigInteger value) {
        return new BigIntType(value);
    }

    @Override
    public BigInteger modImpl(BigInteger v) {
        return value.remainder(v);
    }

    @Override
    public BigInteger addImpl(BigInteger v) {
        return value.add(v);
    }

    @Override
    public BigInteger subtractImpl(BigInteger v) {
        return value.subtract(v);
    }

    @Override
    public BigInteger multiplyImpl(BigInteger v) {
        return value.multiply(v);
    }

    @Override
    public BigInteger divideImpl(BigInteger v) {
        return value.divide(v);
    }

    @Override
    public BigInteger negateImpl() {
        return value.negate();
    }

    @Override
    protected BigInteger absImpl() {
        return value.abs();
    }
}
