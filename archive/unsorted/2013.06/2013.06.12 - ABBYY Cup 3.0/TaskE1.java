package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskE1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long MOD = 1000000000l;
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            if (t == 1) {
                int x = in.nextInt() - 1;
                int v = in.nextInt();
                a[x] = v;
            } else {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;

                long sum = a[l];
                long q = 0;
                long p = 1;
                for (int j = 1; j < r - l + 1; j++) {
                    long cur = (p + q) % MOD;
                    q = p;
                    p = cur;
                    sum = (sum + a[j + l] * cur) % MOD;
                }
                out.println(sum);
            }
        }
    }
}
