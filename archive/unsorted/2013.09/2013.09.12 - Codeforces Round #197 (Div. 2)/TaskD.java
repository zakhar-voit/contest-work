package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;

public class TaskD {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int a[] = new int[1 << n];

        for (int i = 0; i < (1 << n); i++) {
            a[i] = in.nextInt();
        }

        SegmentTree tree = new SegmentTree(a);

        for (int i = 0; i < m; i++) {
            int p = in.nextInt() - 1;
            int b = in.nextInt();

            tree.update(1, p, b);
            out.println(tree.get());
        }
    }

    class SegmentTree {
        int[] tree;
        int[] height;
        int[] left;
        int[] right;

        public SegmentTree(int[] a) {
            int n = a.length;
            tree = new int[4 * n];
            left = new int[4 * n];
            right = new int[4 * n];
            height = new int[4 * n];
            precalc(1, 0, n - 1);
            build(1, a);
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

        void build(int v, int[] arr) {
            if (left[v] == right[v]) {
                tree[v] = arr[left[v]];
                height[v] = 1;
                return;
            }

            build(2 * v, arr);
            build(2 * v + 1, arr);

            height[v] = height[2 * v] ^ 1;
            tree[v] = calc(v);
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

            tree[v] = calc(v);
        }

        int get() {
            return tree[1];
        }

        int calc(int v) {
            return height[v] % 2 == 0 ? tree[2 * v] | tree[2 * v + 1] : tree[2 * v] ^ tree[2 * v + 1];
        }
    }
}
