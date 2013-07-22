package tasks;

import lib.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelOnMars {
    public int minTimes(int[] range, int startCity, int endCity) {
        int n = range.length;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = startCity; i != (endCity + 1) % n; i = (i + 1) % n) {
            arr.add(range[i]);
        }
        int ans = solve(ArrayUtils.toIntArray(arr));
        arr.clear();
        for (int i = startCity; i != mod(endCity - 1, n); i = mod(i - 1, n)) {
            arr.add(range[i]);
        }
        ans = Math.min(ans, solve(ArrayUtils.toIntArray(arr)));
        return ans;
    }

    int mod(int x, int n) {
        while (x < 0)
            x += n;
        return x % n;
    }

    int solve(int[] range) {
        int n = range.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            if (dp[i] == -1)
                continue;
            for (int j = i + 1; j <= Math.min(n - 1, i + range[i]); j++) {
                if (dp[j] == -1 || dp[j] > dp[i] + 1)
                    dp[j] = dp[i] + 1;
            }
        }
        return dp[n - 1];
    }
}
