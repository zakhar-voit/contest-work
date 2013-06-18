package lib.graph;

import java.util.List;

/**
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public interface WeightedGraph<Weight extends Number> {
    public void addEdge(int from, int to, Weight weight);

    public void addEdge(WeightedEdge<Weight> edge);

    public <E extends WeightedEdge<Weight>> E getEdge(int from, int to);

    public int getSize();

    public <E extends WeightedEdge<Weight>> List<E> getAdjacentEdges(int node);

    public List<Integer> getAdjacentNodes(int node);
}
