package lib.collections;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
public class Triplet<T1, T2, T3> {
    T1 a;
    T2 b;
    T3 c;

    public Triplet(T1 a, T2 b, T3 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T1 getA() {
        return a;
    }

    public T2 getB() {
        return b;
    }

    public T3 getC() {
        return c;
    }
}
