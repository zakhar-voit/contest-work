package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskA1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s = in.nextToken();
        int cnt = 0;
        int cnt1 = 0;
        boolean[] vis = new boolean[300];

        for (char c : s.toCharArray()) {
            if ('A' <= c && c <= 'Z' && !vis[c]) {
                cnt++;
                vis[c] = true;
            }
            if (c == '?')
                cnt1++;
        }

        if (cnt > 10) {
            out.println(0);
            return;
        }

        long res = p(10, cnt);
        if ('A' <= s.charAt(0) && s.charAt(0) <= 'Z') {
            res = res - p(9, cnt - 1);
        }

        int cnt2 = 0;
        if (s.charAt(0) == '?') {
            ++cnt2;
            --cnt1;
        }

        res = res * pow(9l, cnt2);

        out.print(res);
        for (int i = 0; i < cnt1; i++)
            out.print("0");
        out.println();
    }

    long pow(long a, int n) {
        if (n == 0) {
            return 1l;
        }
        for (int i = 1; i < n; i++) {
            a *= a;
        }
        return a;
    }

    long p(int n, int k) {
        long res = 1l;
        for (int i = n - k + 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
