package lib.numeric;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public abstract class Numeric extends Number {
    abstract public Numeric add(Numeric other);

    abstract public Numeric subtract(Numeric other);

    abstract public Numeric multiply(Numeric other);

    abstract public Numeric divide(Numeric other);
}
