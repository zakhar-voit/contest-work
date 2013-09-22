package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class antimonotonicity {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        a = compress(a);

        int maxNumber = 0;
        for (int x : a) {
            maxNumber = Math.max(x, maxNumber);
        }

        SegmentTree[] dp = new SegmentTree[2];
        for (int i = 0; i < 2; i++) {
            dp[i] = new SegmentTree(maxNumber + 1, 0);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int[] newVal = new int[2];
            Arrays.fill(newVal, 0);
            for (int j = 0; j < 2; j++) {
                int best;
                if (j == 0) {
                    best = dp[j ^ 1].get(1, 0, a[i] - 1);
                } else {
                    best = dp[j ^ 1].get(1, a[i] + 1, maxNumber);
                }

                if (best != 0 || j != 1) {
                    newVal[j] = Math.max(
                        dp[j].get(1, a[i], a[i]),
                        best + 1);
                }
            }

            for (int j = 0; j < 2; j++) {
                max = Math.max(newVal[j], max);
                dp[j].update(1, a[i], newVal[j]);
            }
        }

        out.println(max);
    }

    int[] compress(int[] a) {
        int[] b = a.clone();
        Arrays.sort(b);

        for (int i = 0; i < a.length; i++) {
            a[i] = Arrays.binarySearch(b, a[i]);
        }

        return a;
    }

    class SegmentTree {
        int[] tree, left, right;
        int defaultValue;

        SegmentTree(int n, int defaultValue) {
            tree = new int[4 * n];
            left = new int[4 * n];
            right = new int[4 * n];

            this.defaultValue = defaultValue;

            Arrays.fill(tree, defaultValue);
            precalc(1, 0, n - 1);
        }

        void precalc(int v, int l, int r) {
            left[v] = l;
            right[v] = r;

            if (l == r)
                return;

            int m = (l + r) / 2;

            precalc(2 * v, l, m);
            precalc(2 * v + 1, m + 1, r);
        }

        void update(int v, int pos, int val) {
            if (left[v] == pos && right[v] == pos) {
                tree[v] = val;
                return;
            }

            if (pos <= right[2 * v])
                update(2 * v, pos, val);
            else
                update(2 * v + 1, pos, val);

            tree[v] = Math.max(tree[2 * v], tree[2 * v + 1]);
        }

        int get(int v, int l, int r) {
            if (r < l)
                return defaultValue;
            if (l <= left[v] && right[v] <= r) {
                return tree[v];
            }

            int max = 0;
            if (l <= right[2 * v])
                max = Math.max(get(2 * v, l, r), max);
            if (r >= left[2 * v + 1])
                max = Math.max(get(2 * v + 1, l, r), max);

            return max;
        }
    }
}
