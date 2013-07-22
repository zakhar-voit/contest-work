package lib.geometry;

import lib.DoubleUtils;
import lib.IO.Scanner;
import lib.collections.Pair;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class Line {
    Point a, b;

    public Line(Point a, Point b) {
        if (a.equals(b))
            throw new RuntimeException("Infinity number of lines contains this points");
        this.a = a;
        this.b = b;
    }

    public Line(double a, double b, double c) {
        if (DoubleUtils.equal(a, 0) && DoubleUtils.equal(b, 0))
            throw new RuntimeException("In classical line equation a * b != 0");

        if (DoubleUtils.equal(b, 0)) {
            // Vertical line
            double x = -c / a;
            this.a = new Point(x, 0);
            this.b = new Point(x, 1);
        } else {
            this.a = new Point(0, -c / b);
            this.b = new Point(1, (-c - a) / b);
        }
    }

    public static Line readLineFromCoefficients(Scanner in) {
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();
        return new Line(a, b, c);
    }

    public Point getDirection() {
        return b.subtract(a);
    }

    public boolean contains(Point o) {
        // Check if square of triangle OAB == 0
        return DoubleUtils.equal(a.subtract(o).vectorProduct(b.subtract(o)), 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Line))
            return false;
        Line o = (Line) obj;
        Point res = null;
        int ans = _intersect(o).first;
        return ans == 2;
    }

    Pair<Integer, Point> _intersect(Line o) {
        Point v = b.subtract(a);
        Point c = o.a;
        Point d = o.b;
        double s1 = Utils.getOrientedArea(a, c, d);
        double s2 = Utils.getOrientedArea(b, d, c);
        double s = s1 + s2;

        if (DoubleUtils.equal(s, 0)) {
            if (o.contains(a)) {
                return new Pair<>(2, a);
            }
            return new Pair<>(0, null);
        }

        v = v.divide(s).multiply(s1);
        return new Pair<>(1, a.add(v));
    }

    public Point getHeight(Point c) {
        Point u = getDirection().invert();
        Point v = c.subtract(b);
        double s = u.vectorProduct(v);
        double len = s / u.length();
        Point res = getDirection().normalize(len);
        return c.add(res.rotate90());
    }

}
