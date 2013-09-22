package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String s[] = in.nextToken().split("\\+");
        int a[] = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            out.print(a[i] + (i == a.length - 1 ? "\n" : "+"));
        }
    }
}
