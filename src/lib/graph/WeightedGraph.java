package lib.graph;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public interface WeightedGraph<Node, Edge extends WeightedEdge<Node, Weight>, Weight extends Number>
        extends Graph<Node, Edge> {
    void addEdge(Node from, Node to, Weight weight);

    Weight getWeight(Node from, Node to);
}
