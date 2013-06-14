package lib.numeric;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class ZeroOrOne extends Numeric {
    boolean value;

    public ZeroOrOne(boolean value) {
        this.value = value;
    }

    public boolean booleanValue() {
        return value;
    }

    @Override
    public int intValue() {
        return byteValue();
    }

    @Override
    public long longValue() {
        return byteValue();
    }

    @Override
    public float floatValue() {
        return byteValue();
    }

    @Override
    public double doubleValue() {
        return byteValue();
    }

    @Override
    public byte byteValue() {
        return value ? (byte) 1 : (byte) 0;
    }

    @Override
    public short shortValue() {
        return byteValue();
    }

    @Override
    public Numeric add(Numeric other) {
        if (other instanceof ZeroOrOne)
            return new ZeroOrOne(value || ((ZeroOrOne) other).value);

        throw new IllegalArgumentException();
    }

    @Override
    public Numeric subtract(Numeric other) {
        return add(other);
    }

    @Override
    public Numeric multiply(Numeric other) {
        if (other instanceof ZeroOrOne)
            return new ZeroOrOne(value && ((ZeroOrOne) other).value);

        throw new IllegalArgumentException();
    }

    @Override
    public Numeric divide(Numeric other) {
        if (other instanceof ZeroOrOne) {
            if (!((ZeroOrOne) other).value)
                throw new IllegalArgumentException("Division by zero");

            return new ZeroOrOne(value && ((ZeroOrOne) other).value);
        }

        throw new IllegalArgumentException();
    }
}
