package tasks;

import lib.IO.Scanner;
import lib.collections.ComparablePair;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class rdeaalbe {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        Map<String, Integer> cnt = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = in.nextToken();

            String key = simplify(s);
            if (!cnt.containsKey(key))
                cnt.put(key, 0);
            cnt.put(key, cnt.get(key) + 1);
        }

        int m = in.nextInt();

        out.println("Scenario #" + testNumber + ":");
        for (int i = 0; i < m; i++) {
            String line = in.next();
            String[] words = line.split(" ");
            int ans = 1;
            for (String word : words) {
                String key = simplify(word);
                if (cnt.containsKey(key))
                    ans *= cnt.get(key);
                else
                    ans = 0;
            }
            out.println(ans);
        }
        out.println();
    }

    String simplify(String s) {
        if (s.length() == 1)
            return s;
        String q = s.length() > 0 ? s.charAt(0) + "" + s.charAt(s.length() - 1) : "";

        char[] middle = s.length() > 2 ? s.substring(1, s.length() - 1).toCharArray() : "".toCharArray();
        Arrays.sort(middle);

        return q + "*" + new String(middle);
    }
}
