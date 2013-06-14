package lib.graph;

import java.util.List;
import java.util.Set;

/**
 * Graph interface. Represents directed and undirected graph;
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public interface Graph<Node, Edge extends lib.graph.Edge<Node>> extends Iterable<Node> {
    public void addNode(Node node);

    public void addEdge(Node from, Node to);

    public void addEdge(Edge edge);

    public boolean containsNode(Node node);

    public boolean containsEdge(Edge edge);

    public boolean containsEdge(Node from, Node to);

    public Edge getEdge(Node from, Node to);

    public Set<Node> getNodes();

    public List<Edge> getAdjacentEdges(Node node);
}
