package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class exchange {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        while (n != 0) {
            double[] prices = new double[n];

            for (int i = 0; i < n; i++) {
                prices[i] = in.nextDouble();
            }

            double[][] dp = new double[n + 1][2];

            dp[0][0] = 1000;

            for (int i = 1; i < n + 1; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < i; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[k][j]);
                        dp[i][j] = Math.max(
                                dp[i][j],
                                deleteFractions(dp[k][j ^ 1] * getPrice(j, prices[i - 1]) * 0.97));
                    }
                }
            }

            out.println(deleteFractions(dp[n][0]));

            n = in.nextInt();
        }
    }

    double deleteFractions(double x) {
        return Math.floor(x * 100) / 100.0;
    }

    double getPrice(int moneyType, double price) {
        if (moneyType == 0)
            return price;
        return 1.0 / price;
    }
}
