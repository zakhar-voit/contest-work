package lib.graph;

import java.util.List;

/**
 * UnweightedGraph interface. Represents directed and undirected graph;
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public interface UnweightedGraph {
    public void addEdge(int from, int to);

    public void addEdge(Edge edge);

    public <E extends Edge> E getEdge(int from, int to);

    public int getSize();

    public <E extends Edge> List<E> getAdjacentEdges(int node);

    public List<Integer> getAdjacentNodes(int node);
}
