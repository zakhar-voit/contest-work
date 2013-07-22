package tasks;

import lib.graph.GraphFactory;
import lib.graph.UnweightedGraph;
import lib.graph.algorithms.BreadthFirstSearcher;

public class Egalitarianism {
    public int maxDifference(String[] isFriend, int d) {
        int n = isFriend.length;
        UnweightedGraph g = GraphFactory.getUnweightedUndirectedGraph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isFriend[i].charAt(j) == 'Y')
                    g.addEdge(i, j);
            }
        }
        BreadthFirstSearcher bfs = new BreadthFirstSearcher(g);
        int ans = 0;
        for (int root = 0; root < n; root++) {
            bfs.bfs(root);
            for (int i = 0; i < n; i++) {
                if (bfs.getDistance(root, i) == -1)
                    ans = -1;
                if (ans != -1)
                    ans = Math.max(bfs.getDistance(root, i), ans);
            }
        }

        return ans <= 0 ? -1 : ans * d;
    }
}
