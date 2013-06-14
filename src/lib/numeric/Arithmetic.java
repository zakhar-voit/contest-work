package lib.numeric;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This class provides basic arithmetic operations.
 * <p/>
 * Supported types:
 * Primitive types: Integer, Long, Byte, Short, Float, Double.
 * Types that that implements Numeric interface.
 * <p/>
 * For other types throws NoNumericValueException.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class Arithmetic {
    static public <T extends Number> boolean isNumeric(T x) {
        return x instanceof Integer ||
                x instanceof Long ||
                x instanceof Double ||
                x instanceof Float ||
                x instanceof Short ||
                x instanceof Byte ||
                x instanceof BigInteger ||
                x instanceof BigDecimal ||
                x instanceof Numeric;
    }

    static public <T extends Number> Number add(T a, T b) {
        if (!isNumeric(a))
            throw new IllegalArgumentException();
        if (a instanceof Integer)
            return (Integer) a + (Integer) b;
        if (a instanceof Double)
            return (Double) a + (Double) b;
        if (a instanceof Long)
            return (Long) a + (Long) b;
        if (a instanceof Float)
            return (Float) a + (Float) b;
        if (a instanceof Short)
            return (Short) a + (Short) b;
        if (a instanceof Byte)
            return (Byte) a + (Byte) b;
        if (a instanceof BigInteger)
            return ((BigInteger) a).add((BigInteger) b);
        if (a instanceof BigDecimal)
            return ((BigDecimal) a).add((BigDecimal) b);
        return ((Numeric) a).add((Numeric) b);
    }

    static public <T extends Number> Number subtract(T a, T b) {
        if (!isNumeric(a))
            throw new IllegalArgumentException();
        if (a instanceof Integer)
            return (Integer) a - (Integer) b;
        if (a instanceof Double)
            return (Double) a - (Double) b;
        if (a instanceof Long)
            return (Long) a - (Long) b;
        if (a instanceof Float)
            return (Float) a - (Float) b;
        if (a instanceof Short)
            return (Short) a - (Short) b;
        if (a instanceof Byte)
            return (Byte) a - (Byte) b;
        if (a instanceof BigInteger)
            return ((BigInteger) a).subtract((BigInteger) b);
        if (a instanceof BigDecimal)
            return ((BigDecimal) a).subtract((BigDecimal) b);
        return ((Numeric) a).subtract((Numeric) b);
    }

    static public <T extends Number> Number multiply(T a, T b) {
        if (!isNumeric(a))
            throw new IllegalArgumentException();
        if (a instanceof Integer)
            return (Integer) a * (Integer) b;
        if (a instanceof Double)
            return (Double) a * (Double) b;
        if (a instanceof Long)
            return (Long) a * (Long) b;
        if (a instanceof Float)
            return (Float) a * (Float) b;
        if (a instanceof Short)
            return (Short) a * (Short) b;
        if (a instanceof Byte)
            return (Byte) a * (Byte) b;
        if (a instanceof BigInteger)
            return ((BigInteger) a).multiply((BigInteger) b);
        if (a instanceof BigDecimal)
            return ((BigDecimal) a).multiply((BigDecimal) b);
        return ((Numeric) a).multiply((Numeric) b);
    }

    static public <T extends Number> Number divide(T a, T b) {
        if (!isNumeric(a))
            throw new IllegalArgumentException();
        if (a instanceof Integer)
            return (Integer) a / (Integer) b;
        if (a instanceof Double)
            return (Double) a / (Double) b;
        if (a instanceof Long)
            return (Long) a / (Long) b;
        if (a instanceof Float)
            return (Float) a / (Float) b;
        if (a instanceof Short)
            return (Short) a / (Short) b;
        if (a instanceof Byte)
            return (Byte) a / (Byte) b;
        if (a instanceof BigInteger)
            return ((BigInteger) a).divide((BigInteger) b);
        if (a instanceof BigDecimal)
            return ((BigDecimal) a).divide((BigDecimal) b);
        return ((Numeric) a).divide((Numeric) b);
    }
}
