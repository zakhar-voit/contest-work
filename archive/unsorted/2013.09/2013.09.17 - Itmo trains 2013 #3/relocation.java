package tasks;

import lib.IO.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class relocation {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int[] c = new int[2];
        int n = in.nextInt();
        for (int i = 0; i < 2; i++) {
            c[i] = in.nextInt();
        }

        int[] w = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }

        int best = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << n); mask++) {
            int cnt1 = 0, cnt2 = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0)
                    ++cnt1;
                else
                    ++cnt2;
            }
            int[] w1 = new int[cnt1];
            int[] w2 = new int[cnt2];

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    w1[--cnt1] = w[i];
                } else {
                    w2[--cnt2] = w[i];
                }
            }

            int ans1 = countTrips(w1, c[0]);
            if (ans1 == -1 || ans1 > best)
                continue;
            int ans2 = countTrips(w2, c[1]);
            if (ans2 == -1)
                continue;
            best = Math.min(best, Math.max(ans1, ans2));
        }

        out.println("Scenario #" + testNumber + ":");
        out.println(best + "\n");
    }

    int countTrips(int[] w, int c) {
        if (w.length == 0)
            return 0;

        for (int x : w) {
            if (x > c)
                return -1;
        }

        int ans = 0;
        while (w.length > 0) {
            int mask = getMaxWeight2(w, c);
            if (mask == 0) {
                throw new RuntimeException();
            }
            int cnt = 0;

            for (int i = 0; i < w.length; i++) {
                if ((mask & (1 << i)) == 0)
                    ++cnt;
            }
            int[] newW = new int[cnt];

            for (int i = 0; i < w.length; i++) {
                if ((mask & (1 << i)) == 0) {
                    newW[--cnt] = w[i];
                }
            }
            w = newW;
            ++ans;
        }

        return ans;
    }

    int getMaxWeight(int[] w, int c) {
        int n = w.length;
        boolean[][] dp = new boolean[n + 1][c + 1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int x = w[i - 1];
            for (int j = 0; j <= c; j++) {
                dp[i][j] |= dp[i - 1][j];
                if (j + x <= c) {
                    dp[i][j + x] |= dp[i - 1][j];
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= c; i++) {
            if (dp[n][i]) {
                max = Math.max(i, max);
            }
        }

        if (max == 0)
            throw new RuntimeException();

        return getAns(dp, w, n, max);
    }

    int getMaxWeight2(int[] w, int c) {
        int n = w.length;
        int ans = 0;
        int best = 0;
        int bestCnt = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            int sum = 0;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += w[i];
                    ++cnt;
                }
            }
            if (sum <= c && (sum > best || (sum == best && cnt > bestCnt))) {
                best = sum;
                ans = mask;
                bestCnt = cnt;
            }
        }

        return ans;
    }

    int getAns(boolean[][] dp, int[] w, int i, int j) {
        if (j == 0)
            return 0;

        if (dp[i][j] && dp[i - 1][j]) {
            return getAns(dp, w, i - 1, j);
        } else {
            if (!dp[i - 1][j - w[i - 1]])
                throw new RuntimeException();
            return (1 << (i - 1)) | getAns(dp, w, i - 1, j - w[i - 1]);
        }
    }
}
