package lib.graph;

/**
 * Directed edge interface.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */

@SuppressWarnings("unused")
public interface Edge<Node> {
    public Node getFrom();

    public Node getTo();

    public Edge getReverseEdge();
}
