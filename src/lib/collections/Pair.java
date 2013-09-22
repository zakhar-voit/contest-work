package lib.collections;

/**
 * Analog c++ std::pair class
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class Pair<First, Second> {
    public First first;
    public Second second;

    public Pair() {

    }

    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        @SuppressWarnings("unchecked")
        Pair<First, Second> other = (Pair<First, Second>) obj;

        return first.equals(other.first) && second.equals(other.second);
    }
}
