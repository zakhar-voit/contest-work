package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class TaskC {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Set<Integer> s = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            while (s.contains(x)) {
                s.remove(x++);
            }
            s.add(x);
        }

        int ans = 0;
        int last = 0;
        for (int x : s) {
            if (x > 0)
                ans += x - last;
            last = x + 1;
        }

        out.println(ans);
    }
}
