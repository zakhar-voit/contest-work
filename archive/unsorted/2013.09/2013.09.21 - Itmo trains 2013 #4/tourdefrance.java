package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class tourdefrance {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (true) {
            int f = in.nextInt();

            if (f == 0)
                return;

            int r = in.nextInt();

            int[] frontTeeth = new int[f];
            int[] rearTeeth = new int[r];

            for (int i = 0; i < f; i++) {
                frontTeeth[i] = in.nextInt();
            }

            for (int i = 0; i < r; i++) {
                rearTeeth[i] = in.nextInt();
            }

            double[] ratios = new double[f * r];

            for (int i = 0; i < f; i++) {
                for (int j = 0; j < r; j++) {
                    ratios[i * r + j] = 1.0 * rearTeeth[j] / frontTeeth[i];
                }
            }

            Arrays.sort(ratios);

            double max = 0;

            for (int i = 0; i < ratios.length - 1; i++) {
                double spread = ratios[i + 1] / ratios[i];

                if (spread > max) {
                    max = spread;
                }
            }

            out.println(Math.round(max * 100) / 100.0);
        }
    }
}
