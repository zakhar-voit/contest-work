package lib.geometry;

import lib.DoubleUtils;
import lib.IO.Scanner;
import lib.collections.Pair;
import lib.collections.Triplet;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class Circle {
    Point a;
    double r;

    public Circle(Point a, double r) {
        this.a = a;
        this.r = r;
    }

    static public Circle readCircle(Scanner in) {
        return new Circle(Point.readPoint(in), in.nextDouble());
    }

    public String toString() {
        return a + " " + r;
    }

    public int numberOfIntersections(Line l) {
        return _intersect(l).getC();
    }

    public Pair<Point, Point> intersect(Line l) {
        Triplet<Point, Point, Integer> res = _intersect(l);
        return new Pair<>(res.getA(), res.getB());
    }

    Triplet<Point, Point, Integer> _intersect(Line l) {
        Point h = l.getHeight(a);
        double heightLength = a.distanceTo(h);

        if (DoubleUtils.great(heightLength, r))
            return new Triplet<>(null, null, 0);

        @SuppressWarnings("all")
        double hc = DoubleUtils.sqrt(DoubleUtils.sqr(r) - DoubleUtils.sqr(heightLength));
        Point v = l.getDirection().normalize(hc);
        Point c = h.add(v);
        Point d = h.subtract(v);

        return new Triplet<>(c, d, c.equals(d) ? 1 : 2);
    }
}
