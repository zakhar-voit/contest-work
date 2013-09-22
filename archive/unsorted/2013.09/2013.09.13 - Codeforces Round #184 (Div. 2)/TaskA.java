package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
        }

        char[][] sd = new char[n][3];

        for (int i = 0; i < n; i++) {
            sd[i] = toStr(d[i]);
        }

        int best = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (d[i] == 0)
                continue;
            char[] used = new char[3];

            int curBest = 1;
            if (curBest > best) {
                best = curBest;
                ans.clear();
                ans.add(i);
            }
            for (int j = 0; j < 3; j++) {
                used[j] = sd[i][j];
            }

            for (int j = 0; j < n; j++) {
                if (d[j] == 0 || j == i)
                    continue;

                boolean ok = true;
                for (int k = 0; k < 3; k++) {
                    if (used[k] == '1' && used[k] == sd[j][k]) {
                        ok = false;
                        break;
                    }
                }

                if (!ok)
                    continue;

                curBest = 2;
                if (curBest > best) {
                    best = curBest;
                    ans.clear();
                    ans.add(i);
                    ans.add(j);
                }

                for (int k = 0; k < 3; k++) {
                    if (sd[j][k] == '1')
                        used[k] = '1';
                }

                for (int k = 0; k < n; k++) {
                    if (d[k] == 0 || k == i || k == j) {
                        continue;
                    }

                    ok = true;
                    for (int l = 0; l < 3; l++) {
                        if (used[l] == '1' && used[l] == sd[k][l]) {
                            ok = false;
                            break;
                        }
                    }

                    if (!ok)
                        continue;

                    curBest = 3;
                    if (curBest > best) {
                        best = curBest;
                        ans.clear();
                        ans.add(i);
                        ans.add(j);
                        ans.add(k);
                    }
                }
            }
        }

        int zeroCnt = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] == 0)
                ++zeroCnt;
        }

        out.println(zeroCnt + best);
        for (int i = 0; i < zeroCnt; i++) {
            out.print("0 ");
        }

        for (int i = 0; i < best; i++) {
            out.print(d[ans.get(i)] + " ");
        }
        out.println();
    }

    char[] toStr(int x) {
        char[] res = new char[3];
        String s = ((Integer)x).toString();
        while (s.length() < 3)
            s = "0" + s;
        res = s.toCharArray();

        for (int i = 0; i < res.length; i++) {
            if (res[i] != '0')
                res[i] = '1';
        }
        return res;
    }
}
