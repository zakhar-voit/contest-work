package lib.graph;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class GraphFactory {
    static public UnweightedGraph getUnweightedDirectedGraph(int size) {
        return new Graph(size) {
        };
    }

    static public UnweightedGraph getUnweightedUndirectedGraph(int size) {
        return new Graph(size) {
            @Override
            public void addEdge(Edge edge) {
                super.addEdge(edge);
                super.addEdge(edge.getReverseEdge());
            }
        };
    }

    static public <Weight extends Number> WeightedGraph<Weight> getWeightedDirectedGraph(int size) {
        return new Graph<Weight>(size) {
        };
    }

    static public <Weight extends Number> WeightedGraph<Weight> getWeightedUndirectedGraph(int size) {
        return new Graph<Weight>(size) {
            @Override
            public void addEdge(WeightedEdge<Weight> edge) {
                super.addEdge(edge);
                super.addEdge(edge.getReverseEdge());
            }
        };
    }
}
