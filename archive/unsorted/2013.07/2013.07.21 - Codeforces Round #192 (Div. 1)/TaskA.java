package tasks;

import lib.IO.Scanner;
import lib.collections.Pair;

import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        boolean[][] a = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            char[] s = in.nextToken().toCharArray();
            for (int j = 0; j < n; j++) {
                a[i][j] = s[j] == '.';
            }
        }

        for (int i = 0; i < n; i++) {
            boolean ok = false;
            for (int j = 0; j < n; j++) {
                ok |= a[i][j];
            }
            for (int j = 0; j < n; j++) {
                ok |= a[j][i];
            }
            if (!ok) {
                out.println(-1);
                return;
            }
        }

        boolean[][] color = new boolean[n][n];
        ArrayList<Pair<Integer, Integer>> ans = new ArrayList<Pair<Integer, Integer>>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] && isBad(color, i, j, true) && isBad(color, i, j, false)) {
                    colorize(color, i, true);
                    colorize(color, j, false);
                    ans.add(new Pair<Integer, Integer>(i, j));
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] && (isBad(color, i, j, true) || isBad(color, i, j, false))) {
                    colorize(color, i, true);
                    colorize(color, j, false);
                    ans.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }

        for (Pair<Integer, Integer> cur : ans) {
            out.println((cur.first + 1) + " " + (cur.second + 1));
        }
    }

    void colorize(boolean[][] color, int idx, boolean row) {
        for (int i = 0; i < color.length; i++) {
            if (row)
                color[idx][i] = true;
            else
                color[i][idx] = true;
        }
    }

    boolean isBad(boolean[][] color, int i, int j, boolean row) {
        for (int k = 0; k < color.length; k++) {
            if ((row && !color[i][k]) || !color[k][j])
                return true;
        }
        return false;
    }
}
