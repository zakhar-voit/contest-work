package tasks;

import lib.IO.Scanner;
import lib.collections.Pair;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class TaskB {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        char[][] a = new char[n][m];

        int doorx = -1;
        int doory = -1;

        int sx = -1;
        int sy = -1;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextToken().toCharArray();

            for (int j = 0; j < m; j++) {
                if (a[i][j] == 'E') {
                    doorx = i;
                    doory = j;
                }

                if (a[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        final int[] dx = {1, -1, 0, 0};
        final int[] dy = {0, 0, 1, -1};

        Queue<Pair<Integer, Integer>> q = new ArrayDeque<Pair<Integer, Integer>>();
        q.add(new Pair<Integer, Integer>(doorx, doory));

        int[][] d = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], -1);
        }

        d[doorx][doory] = 0;
        while (!q.isEmpty()) {
            Pair<Integer, Integer> top = q.poll();
            int i = top.first;
            int j = top.second;
            for (int k = 0; k < 4; k++) {
                int newI = i + dx[k];
                int newJ = j + dy[k];
                if (newI >= 0 && newI < n && newJ >= 0 && newJ < m &&
                        a[newI][newJ] != 'T' && d[newI][newJ] == -1) {
                    d[newI][newJ] = d[i][j] + 1;
                    q.add(new Pair<Integer, Integer>(newI, newJ));
                }
            }
        }

        int cnt = d[sx][sy];
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] >= '0' && a[i][j] <= '9' &&
                        d[i][j] != -1 && d[i][j] <= cnt) {
                    ans += a[i][j] - '0';
                }
            }
        }

        out.println(ans);
    }
}
