package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int cur = 0;
        long ans = 0;
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            if (x < cur) {
                ans += n + x - cur;
            } else {
                ans += x - cur;
            }
            cur = x;
        }

        out.println(ans);
    }
}
