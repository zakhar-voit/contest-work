package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] num = new int[n][m];

        int ans = 1;
        int curMx = 1;
        int cur = 0;
        int all = n * m;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cur == curMx && all >= curMx + 1) {
                    ++curMx;
                    cur = 0;
                    ++ans;
                }
                num[i][j] = curMx;
                ++cur;
                --all;
            }
        }

        out.println(ans);
        int last = -1;
        char c = 'A' - 1;
        List<Character>[] field = (List<Character>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            boolean allbad = false;
            field[i] = new ArrayList<Character>();
            for (int j = 0; j < m; j++) {
                field[i].add('#');
            }
            for (int j = 0; j < m; j++) {
                if (last != num[i][j]) {
                    if (c == 'Z')
                        c = 'A';
                    else {
                        if (i > 0) {
                            boolean[] vis = new boolean['Z' - 'A' + 1];
                            vis[c - 'A'] = true;
                            for (int k = j; k < Math.min(m, j + num[i][j]); k++) {
                                vis[field[i - 1].get(k) - 'A'] = true;
                            }
                            for (int k = 0; k < Math.min(j, num[i][j] - m + j); k++) {
                                vis[field[i].get(k) - 'A'] = true;
                            }
                            allbad = true;
                            for (int k = 0; k < 'Z' - 'A' + 1; k++) {
                                if (!vis[k])
                                    allbad = false;
                            }

                            if (allbad) {
                                   break;
                            }

                            while (vis[c - 'A']) {
                                ++c;
                                if (c == 'Z')
                                    c = 'A';
                            }
                        } else {
                            ++c;
                        }
                    }
                }

                if (allbad)
                    break;

                field[i].set(j, c);
                last = num[i][j];
            }
        }

        last = '#';
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1)
                Collections.reverse(field[i]);
            for (int j = 0; j < m; j++) {
                if (field[i].get(j).equals('#')) {
                    out.print(last);
                } else {
                    out.print(field[i].get(j));
                    last = field[i].get(j);
                }
            }
            out.println();
        }
    }
}
