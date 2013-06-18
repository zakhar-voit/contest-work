package lib.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents weighted directed. Implemented using adjacency lists.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
abstract public class Graph<Weight extends Number> implements WeightedGraph<Weight>, UnweightedGraph {

    List<WeightedEdge<Weight>> adjacencyLists[];

    int size;

    @SuppressWarnings("unchecked")
    public Graph(int size) {
        this.size = size;
        adjacencyLists = (ArrayList<WeightedEdge<Weight>>[]) new ArrayList[size];
        for (int i = 0; i < size; i++) {
            adjacencyLists[i] = new ArrayList<WeightedEdge<Weight>>();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void addEdge(Edge edge) {
        addEdge(new WeightedEdge<Weight>(edge.getFrom(), edge.getTo(), null));
    }

    public void addEdge(int from, int to, Weight weight) {
        addEdge(new WeightedEdge<Weight>(from, to, weight));
    }

    public Weight getWeight(int from, int to) {
        return getEdge(from, to).getWeight();
    }

    @Override
    public void addEdge(int from, int to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(WeightedEdge<Weight> edge) {
        if (!containsNode(edge.getFrom()))
            throw new NoSuchNodeException();

        adjacencyLists[edge.getFrom()].add(edge);
    }

    public boolean containsNode(int node) {
        return node >= 0 && node <= size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WeightedEdge<Weight>> getAdjacentEdges(int node) {
        if (node < 0 || node >= size)
            throw new NoSuchNodeException();
        return adjacencyLists[node];
    }

    @Override
    public List<Integer> getAdjacentNodes(int node) {
        List<WeightedEdge<Weight>> edges = getAdjacentEdges(node);
        List<Integer> res = new ArrayList<Integer>(edges.size());
        for (WeightedEdge<Weight> edge : edges) {
            res.add(edge.to);
        }
        return res;
    }

    @Override
    @SuppressWarnings("unchecked")
    public WeightedEdge<Weight> getEdge(int from, int to) {
        if (!containsNode(from))
            throw new NoSuchNodeException();
        for (WeightedEdge<Weight> edge : adjacencyLists[from]) {
            if (edge.getFrom() == from && edge.getTo() == to) {
                return edge;
            }
        }

        throw new NoSuchEdgeException();
    }
}
