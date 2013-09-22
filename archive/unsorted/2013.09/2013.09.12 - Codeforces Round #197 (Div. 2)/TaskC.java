package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        boolean[] has = new boolean[10];
        String s = in.nextToken();
        for (int i = 0; i < 10; i++) {
            has[i] = s.charAt(i) == '1';
        }

        int m = in.nextInt();

        int[] ans = new int[m];
        boolean[][][] dp = new boolean[m][20][10];
        int[][][] p = new int[m][20][10];

        int INF = (int)1e9;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 10; k++) {
                    dp[i][j][k] = false;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            if (has[i]) {
                dp[0][i + 1][i] = true;
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 10; k++) {
                    if (!has[k] || !dp[i][j][k])
                        continue;
                    for (int l = 0; l < 10; l++) {
                        if (!has[l] || l == k || j - l - 1 >= 0)
                            continue;
                        dp[i + 1][l + 1 - j][l] = true;
                        p[i + 1][l + 1 - j][l] = k;
                    }
                }
            }
        }

        boolean ok = false;
        for (int i = 0; !ok && i < 20; i++) {
            for (int j = 0; !ok && j < 10; j++) {
                if (dp[m - 1][i][j]) {
                    int curI = i;
                    int curJ = j;
                    ok = true;
                    for (int k = m - 1; k >= 0; k--) {
                        ans[k] = curJ + 1;
                        int newJ = p[k][curI][curJ];
                        curI = curJ + 1 - curI;
                        curJ = newJ;
                    }
                }
            }
        }

        if (ok) {
            out.println("YES");
            for (int i = 0; i < m; i++) {
                out.print(ans[i] + " ");
            }
            out.println();
        } else {
            out.println("NO");
        }

    }
}
