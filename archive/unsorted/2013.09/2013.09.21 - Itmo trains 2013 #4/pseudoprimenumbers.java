package tasks;

import lib.IO.Scanner;

import java.io.PrintWriter;

public class pseudoprimenumbers {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        while (true) {
            int p = in.nextInt();
            int a = in.nextInt();

            if (a == 0 && p == 0)
                return;

            if (!isPrime(p) && pow(a, p, p) == a % p) {
                out.println("yes");
            } else {
                out.println("no");
            }
        }
    }

    int pow(int a, int n, int mod) {
        int res = 1;

        while (n > 0) {
            if (n % 2 == 1)
                res = ((int) ((1l * res * a) % mod));
            a = ((int) ((1l * a * a) % mod));
            n /= 2;
        }

        return res;
    }

    boolean isPrime(int x) {
        if (x == 1)
            return false;
        if (x == 2)
            return true;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0)
                return false;
        }

        return true;
    }
}
