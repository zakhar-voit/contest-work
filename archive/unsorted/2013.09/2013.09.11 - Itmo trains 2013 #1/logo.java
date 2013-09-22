package tasks;

import lib.IO.Scanner;

import java.io.PrintWriter;

public class logo {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        double x = 0;
        double y = 0;

        int angle = 0;

        int[] a = new int[10];

        for (int i = 0; i < n; i++) {
            String command = in.nextToken();
            int value = in.nextInt();

            switch (command) {
                case "lt":
                    angle += value;

                    break;
                case "rt":
                    angle -= value;

                    break;
                case "fd":
                    x += Math.cos(Math.PI * angle / 180) * value;
                    y += Math.sin(Math.PI * angle / 180) * value;

                    break;
                case "bk":
                    x -= Math.cos(Math.PI * angle / 180) * value;
                    y -= Math.sin(Math.PI * angle / 180) * value;

                    break;
                default:
                    throw new RuntimeException();
            }
        }

        out.println((int)Math.round(Math.sqrt(x * x + y * y)));
    }
}
