package tasks;

import lib.IO.Scanner;
import lib.collections.Pair;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class honeymoon {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        final int[] dx = {1, -1, 0, 0};
        final int[] dy = {0, 0, 1, -1};

        int n = in.nextInt();

        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int ans = Integer.MAX_VALUE;
        boolean[][] vis = new boolean[n][n];
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        for (int low = 0; low <= 200; low++) {
            int l = low - 1;
            int r = 200;

            boolean[] ok = new boolean[201];

            for (int high = low; high <= 200; high++) {
//            while (r - l > 1) {
//                int high = (l + r) / 2;
                q.clear();

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        vis[i][j] = false;
                    }
                }

                if (a[0][0] >= low && a[0][0] <= high) {
                    vis[0][0] = true;
                    q.add(new Pair<>(0, 0));
                }
                while (!q.isEmpty()) {
                    Pair<Integer, Integer> top = q.poll();
                    int x = top.first;
                    int y = top.second;

                    for (int i = 0; i < 4; i++) {
                        int newX = x + dx[i];
                        int newY = y + dy[i];

                        if (newX < n && newX >= 0 && newY < n && newY >= 0
                                && !vis[newX][newY]
                                && low <= a[newX][newY]
                                && a[newX][newY] <= high) {

                            vis[newX][newY] = true;
                            q.add(new Pair<>(newX, newY));

                            if (newX == n - 1 && newY == n - 1)
                                break;
                        }
                    }
                }

                if (vis[n - 1][n - 1]) {
                    r = high;
                    ok[r] = true;
                    break;
                } else {
                    l = high;
                }
            }

            if (ok[r]) {
                ans = Math.min(r - low, ans);
            }
        }

        out.println("Scenario #" + testNumber + ":");
        out.println(ans + "\n");
    }
}
