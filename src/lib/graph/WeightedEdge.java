package lib.graph;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public interface WeightedEdge<Node, Weight extends Number> extends Edge<Node> {
    public Weight getWeight();
}
