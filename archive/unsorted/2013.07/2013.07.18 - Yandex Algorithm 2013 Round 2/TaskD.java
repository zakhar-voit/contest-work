package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int cnt = 0;
        char ans = '!';
        if (a + b == c) {
            ++cnt;
            ans = '+';
        }
        if (a - b == c) {
            ++cnt;
            ans = '-';
        }
        if (1l * a * b == c) {
            ++cnt;
            ans = '*';
        }
        if (b != 0 && a % b == 0 && a / b == c) {
            ++cnt;
            ans = '/';
        }

        if (cnt > 1 || cnt == 0) {
            out.println("Invalid");
        } else {
            out.println(ans);
        }
    }
}
