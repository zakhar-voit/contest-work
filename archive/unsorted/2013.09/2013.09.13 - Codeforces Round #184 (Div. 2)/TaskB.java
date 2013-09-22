package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        long p = in.nextLong();
        long q = in.nextLong();
        int n = in.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        if (a[n - 1] == 1) {
            if (n >= 2) {
                ++a[n - 2];
                --n;
            }
        }

        for (int i = 0; i < n; i++) {
            long intPart = p / q;

            long newq = p % q;
            if (intPart != a[i]) {
                out.println("NO");
                return;
            }

            if (newq == 0) {
                if (i == n - 1) {
                    q = newq;
                    break;
                } else {
                    out.println("NO");
                    return;
                }
            }
            p = q;
            q = newq;
        }

        if (q == 0)
            out.println("YES");
        else
            out.println("NO");
    }
}
