package lib.graph;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class WeightedUndirectedGraph<Node, Weight extends Number>
        extends WeightedDirectedGraph<Node, Weight> {
    @Override
    public void addEdge(Edge<Node, Weight> edge) {
        super.addEdge(edge);
        super.addEdge(edge.getReverseEdge());
    }
}
