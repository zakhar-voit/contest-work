package lib.geometry;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
public class Utils {
    static public double getOrientedArea(Point a, Point b, Point c) {
        return b.subtract(a).vectorProduct(c.subtract(a));
    }
}
