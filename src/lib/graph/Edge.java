package lib.graph;

/**
 * Directed edge interface.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */

@SuppressWarnings("unused")
public interface Edge {
    public int getFrom();

    public int getTo();

    public <E extends Edge> E getReverseEdge();
}
