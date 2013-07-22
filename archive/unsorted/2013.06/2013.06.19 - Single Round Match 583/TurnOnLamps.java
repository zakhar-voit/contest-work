package tasks;

import java.util.ArrayList;
import java.util.Arrays;

public class TurnOnLamps {
    public int minimize(int[] roads, String initState, String isImportant) {
        n = roads.length + 1;
        g = new boolean[n][n];
        this.isImportant = new boolean[n][n];
        this.initState = new boolean[n][n];
        for (int i = 0; i < n - 1; i++) {
            if (isImportant.charAt(i) == '1')
                this.isImportant[i + 1][roads[i]] = this.isImportant[roads[i]][i + 1] = true;
            if (initState.charAt(i) == '1')
                this.initState[i + 1][roads[i]] = this.initState[roads[i]][i + 1] = true;
            g[i + 1][roads[i]] = g[roads[i]][i + 1] = true;
        }

        vis = new boolean[n];
        type = new boolean[n];
        typeImportant = new boolean[n];

        current = new int[n];
        ans = new int[n];

        int best = Integer.MAX_VALUE;
        for (int root = 0; root < n; root++) {
            Arrays.fill(vis, false);
            Arrays.fill(current, 0);
            Arrays.fill(ans, 0);
            calcType(root);
            Arrays.fill(vis, false);
            calc(root);
            best = Math.min(best, ans[root]);
        }

        return best;
    }

    boolean[][] isImportant;
    boolean[][] initState;
    boolean[] typeImportant;
    boolean[] vis;
    boolean[][] g;
    boolean[] type;
    int[] current;
    int[] ans;
    int n;

    void calcType(int v) {
        vis[v] = true;
        for (int i = 0; i < n; i++) {
            if (!vis[i] && g[i][v]) {
                calcType(i);
                if (isImportant[v][i])
                    typeImportant[i] = true;
                if (initState[v][i])
                    type[i] = true;
            }
        }
    }

    void calc(int v) {
        vis[v] = true;
        boolean[] vis2 = vis.clone();
        for (int i = 0; i < n; i++) {
            if (!vis[i] && g[i][v]) {
                calc(i);
            }
        }

        if (this.type[v]) {
            ans[v] = 0;
            for (int i = 0; i < n; i++) {
                if (!vis2[i] && g[i][v]) {
                    ans[v] += ans[i];
                    if (current[i] > 0)
                        ++ans[v];
                }
            }
            current[v] = 0;
        } else {
            int cntWhite = 0;
            for (int i = 0; i < n; i++) {
                ans[v] += ans[i];
                if (current[i] > 0) {
                    ++cntWhite;
                }
            }
            if (cntWhite >= 2) {
                current[v] = 0;
                ans[v] += cntWhite - 1;
            } else if (cntWhite == 1) {
                current[v] = 1;
            } else {
                current[v] = typeImportant[v] ? 1 : 0;
            }
        }
    }
}
