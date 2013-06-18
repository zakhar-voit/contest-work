package lib.graph;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public final class WeightedEdge<Weight extends Number>
        implements Edge {

    int from;
    int to;
    Weight weight;

    public WeightedEdge(int from, int to, Weight weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Weight getWeight() {
        return weight;
    }

    @Override
    public int getFrom() {
        return from;
    }

    @Override
    public int getTo() {
        return to;
    }

    @Override
    @SuppressWarnings("unchecked")
    public WeightedEdge<Weight> getReverseEdge() {
        return new WeightedEdge<Weight>(to, from, weight);
    }
}
