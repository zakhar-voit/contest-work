package lib.graph.algorithms;

import lib.ArrayUtils;
import lib.collections.FastClearingArray;
import lib.graph.NoSuchNodeException;
import lib.graph.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * This breadth first search.
 * Using to find distances from node to all other nodes in unweighted graph.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class BreadthFirstSearcher {
    UnweightedGraph graph;
    FastClearingArray<Integer>[] distance;
    FastClearingArray<Integer>[] parent;
    final Queue<Integer> queue = new ArrayDeque<>();

    @SuppressWarnings("unchecked")
    public BreadthFirstSearcher(UnweightedGraph graph) {
        this.graph = graph;
        distance = (FastClearingArray<Integer>[]) new FastClearingArray[graph.getSize()];
        parent = (FastClearingArray<Integer>[]) new FastClearingArray[graph.getSize()];
    }

    private void reset(int root) {
        queue.clear();
        queue.add(root);
        if (distance[root] == null) {
            distance[root] = new FastClearingArray<>(graph.getSize(), -1);
            parent[root] = new FastClearingArray<>(graph.getSize(), -1);
        }
        distance[root].clear();
        parent[root].clear();
    }

    private void goTo(int to) {
        queue.add(to);
    }

    void relaxDistance(int root, int from, int to) {
        if (distance[root].get(to).equals(-1)
                || distance[root].get(to) > distance[root].get(from) + 1) {

            distance[root].set(to, distance[root].get(from) + 1);
            parent[root].set(to, from);
        }
    }

    private void goToAdjacentNodes(int root, int from) {
        for (int to : graph.getAdjacentNodes(from)) {
            if (distance[root].get(to).equals(-1)) {
                goTo(to);
                relaxDistance(root, from, to);
            }
        }
    }

    public int getDistance(int from, int to) {
        if (from > graph.getSize() || to >= graph.getSize())
            throw new NoSuchNodeException();
        return distance[from].get(to);
    }

    public int[] getPath(int from, int to) {
        if (getDistance(from, to) == -1)
            throw new RuntimeException("Nodes is not connected");

        ArrayList<Integer> path = new ArrayList<>();
        for (; to != -1; to = parent[from].get(to)) {
            path.add(to);
        }

        Collections.reverse(path);
        return ArrayUtils.toIntArray(path);
    }

    public void bfs(int from) {
        if (from > graph.getSize())
            throw new NoSuchNodeException();

        reset(from);
        distance[from].set(from, 0);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            goToAdjacentNodes(from, currentNode);
        }
    }
}
