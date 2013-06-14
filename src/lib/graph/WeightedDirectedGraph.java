package lib.graph;

import java.util.*;
import java.util.function.Consumer;

/**
 * Class which represents weighted directed. Implemented using adjacency lists.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class WeightedDirectedGraph<Node, Weight extends Number>
        implements WeightedGraph<Node, WeightedDirectedGraph.Edge<Node, Weight>, Weight> {

    final Map<Node, ArrayList<Edge<Node, Weight>>> adjacencyLists = new HashMap<Node, ArrayList<Edge<Node, Weight>>>();

    public static final class Edge<Node, Weight extends Number>
            implements WeightedEdge<Node, Weight> {

        Node from;
        Node to;
        Weight weight;

        public Edge(Node from, Node to, Weight weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public Weight getWeight() {
            return weight;
        }

        @Override
        public Node getFrom() {
            return from;
        }

        @Override
        public Node getTo() {
            return to;
        }

        @Override
        public Edge<Node, Weight> getReverseEdge() {
            return new Edge<Node, Weight>(to, from, weight);
        }
    }

    @Override
    public void addEdge(Node from, Node to, Weight weight) {
        addEdge(new Edge<Node, Weight>(from, to, weight));
    }

    @Override
    public Weight getWeight(Node from, Node to) {
        return getEdge(from, to).getWeight();
    }

    @Override
    public void addNode(Node node) {
        adjacencyLists.put(node, new ArrayList<Edge<Node, Weight>>());
    }

    @Override
    public void addEdge(Node from, Node to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(Edge<Node, Weight> edge) {
        if (!containsNode(edge.getFrom()))
            throw new NoSuchNodeException();

        adjacencyLists.get(edge.getFrom()).add(edge);
    }

    @Override
    public boolean containsNode(Node node) {
        return adjacencyLists.containsKey(node);
    }

    @Override
    @SuppressWarnings("all")
    public boolean containsEdge(Edge<Node, Weight> edge) {
        return containsNode(edge.from) &&
                containsNode(edge.to) &&
                adjacencyLists.get(edge.from).contains(edge.to);
    }

    @Override
    public List<Edge<Node, Weight>> getAdjacentEdges(Node node) {
        return adjacencyLists.get(node);
    }

    @Override
    public boolean containsEdge(Node from, Node to) {
        if (!containsNode(from))
            throw new NoSuchNodeException();

        try {
            Edge<Node, Weight> edge = getEdge(from, to);
        } catch (NoSuchEdgeException e) {
            return false;
        }
        return true;
    }

    @Override
    public Edge<Node, Weight> getEdge(Node from, Node to) {
        if (!containsNode(from))
            throw new NoSuchNodeException();
        for (Edge<Node, Weight> edge : adjacencyLists.get(from)) {
            if (edge.getFrom() == from && edge.getTo() == to) {
                return edge;
            }
        }

        throw new NoSuchEdgeException();
    }

    @Override
    public Set<Node> getNodes() {
        return adjacencyLists.keySet();
    }

    @Override
    public Iterator<Node> iterator() {
        return getNodes().iterator();
    }

    @Override
    public void forEach(Consumer<? super Node> action) {
        getNodes().forEach(action);
    }

}
