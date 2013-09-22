package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class wifi {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] houses = new int[m];

        for (int i = 0; i < m; i++) {
            houses[i] = in.nextInt() * 2;
        }
        Arrays.sort(houses);

        int l = -1;
        int r = 2000100;
        while (r - l > 1) {
            int maxDist = (l + r) / 2;

            int used = 0;
            int pos = -4000000;
            for (int i = 0; i < m; i++) {
                if (Math.abs(pos - houses[i]) > maxDist) {
                    pos = houses[i] + maxDist;
                    ++used;
                }
            }

            if (used <= n)
                r = maxDist;
            else
                l = maxDist;
        }

        out.println((r / 2) + (r % 2 == 1 ? ".5" : ".0"));
    }
}
