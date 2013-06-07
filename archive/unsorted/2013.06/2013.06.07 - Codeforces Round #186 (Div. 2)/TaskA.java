package tasks;



import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String money = in.nextToken();
        int a = Integer.MIN_VALUE;
        int b = Integer.MIN_VALUE;
        int c = Integer.MIN_VALUE;

        try {
            a = Integer.parseInt(money);
        } catch (Exception e) {

        }

        try {
            b = Integer.parseInt(money.substring(0, money.length() - 1));
        } catch (Exception e) {

        }

        try {
            c = Integer.parseInt(money.substring(0, money.length() - 2) + money.substring(money.length() - 1));
        } catch (Exception e) {

        }

        out.println(Math.max(a, Math.max(b, c)));
    }
}
