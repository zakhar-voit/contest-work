package lib.geometry;

import lib.DoubleUtils;
import lib.IO.Scanner;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class Point implements Comparable<Point> {
    static public Point ORIGIN = new Point(0, 0);
    static public Point X_AXIS = new Point(1, 0);
    static public Point Y_AXIS = new Point(0, 1);

    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Point))
            return false;

        Point o = (Point) obj;
        return DoubleUtils.equal(x, o.x) && DoubleUtils.equal(y, o.y);
    }

    public String toString() {
        return x + " " + y;
    }

    public Point add(Point o) {
        return new Point(x + o.x, y + o.y);
    }

    public Point subtract(Point o) {
        return new Point(x - o.x, y - o.y);
    }

    public Point multiply(double k) {
        return new Point(x * k, y * k);
    }

    public Point divide(double k) {
        return new Point(x / k, y / k);
    }

    public Point invert() {
        return new Point(-x, -y);
    }

    public double scalarProduct(Point o) {
        return x * o.x + y * o.y;
    }

    public double vectorProduct(Point o) {
        return x * o.y - y * o.x;
    }

    @SuppressWarnings("all")
    public Point rotate90() {
        return new Point(-y, x);
    }

    public Point rotate(double alpha) {
        return rotate(Math.cos(alpha), Math.sin(alpha));
    }

    public Point rotate(double cosa, double sina) {
        Point v = this;
        Point u = v.rotate90();
        return v.multiply(cosa).add(u.multiply(sina));
    }

    public double length() {
        return Math.sqrt(scalarProduct(this));
    }

    public double distanceTo(Point o) {
        return this.subtract(o).length();
    }

    public boolean collinear(Point o) {
        return DoubleUtils.equal(vectorProduct(o), 0);
    }

    public Point normalize(double k) {
        double len = length();
        if (DoubleUtils.equal(len, 0)) {
            if (DoubleUtils.equal(k, 0))
                return new Point(0, 0);
            throw new RuntimeException("Point has zero length");
        }

        return this.multiply(k / len);
    }

    public static Point readPoint(Scanner in) {
        return new Point(in.nextDouble(), in.nextDouble());
    }

    public int compareTo(Point o) {
        if (x < o.x || (x == o.x && y < o.y))
            return -1;
        if (equals(o))
            return 0;
        return 1;
    }
}