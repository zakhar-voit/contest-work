package tasks;

import lib.IO.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class conformity {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        while (n != 0) {
            Map<String, Integer> cnt = new HashMap<>();

            for (int student = 0; student < n; student++) {
                int[] courses = new int[5];

                for (int i = 0; i < 5; i++) {
                    courses[i] = in.nextInt();
                }
                Arrays.sort(courses);

                StringBuilder builder = new StringBuilder();

                for (int x : courses) {
                    builder.append(x).append(" ");
                }

                String key = new String(builder);

                if (!cnt.containsKey(key)) {
                    cnt.put(key, 1);
                } else {
                    cnt.put(key, cnt.get(key) + 1);
                }
            }

            int max = 0;

            for (int value : cnt.values()) {
                max = Math.max(value, max);
            }

            int ans = 0;
            for (int value : cnt.values()) {
                if (max == value)
                    ans += value;
            }

            out.println(ans);

            n = in.nextInt();
        }
    }
}
