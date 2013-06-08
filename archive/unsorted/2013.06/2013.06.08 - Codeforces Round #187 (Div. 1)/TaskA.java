package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        ArrayList<Integer> unrate = new ArrayList<Integer>();

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long d = sum - 1l * (n - i - 1) * a[i] * (i - unrate.size());
            if (d < k) {
                unrate.add(i);
            } else {
                sum += 1l * (i - unrate.size()) * a[i];
            }
        }

        for (int x : unrate) {
            out.println(x + 1);
        }
    }
}
