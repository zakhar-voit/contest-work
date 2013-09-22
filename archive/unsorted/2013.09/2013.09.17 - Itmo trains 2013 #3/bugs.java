package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class bugs {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        g = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            g[a].add(b);
            g[b].add(a);
        }

        color = new int[n];

        boolean ans = true;
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1)) {
                    ans = false;
                    break;
                }
            }
        }

        out.println("Scenario #" + testNumber + ":");

        if (ans)
            out.println("No suspicious bugs found!\n");
        else
            out.println("Suspicious bugs found!\n");
    }

    int[] color;
    ArrayList<Integer>[] g;

    boolean dfs(int v, int c) {
        color[v] = c;

        for (int to : g[v]) {
            if (color[to] != 0 && color[to] == c) {
                return false;
            } else if (color[to] == 0) {
                if (!dfs(to, next(c)))
                    return false;
            }
        }

        return true;
    }

    int next(int x) {
        return x == 1 ? 2 : 1;
    }
}
