package lib.collections;

/**
 * Analog c++ std::pair with implemented comparable interface
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class ComparablePair<First extends Comparable<First>, Second extends Comparable<Second>>
        extends Pair<First, Second>
        implements Comparable<ComparablePair<First, Second>> {

    public ComparablePair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(ComparablePair<First, Second> o) {
        int res = first.compareTo(o.first);
        if (res != 0)
            return res;
        return second.compareTo(o.second);
    }
}
