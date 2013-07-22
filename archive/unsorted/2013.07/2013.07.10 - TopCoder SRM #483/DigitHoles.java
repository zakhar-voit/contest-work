package tasks;

public class DigitHoles {
    public int numHoles(int number) {
        int[] cnt = {1, 0, 0, 0, 1, 0, 1, 0, 2, 1};

        int ans = 0;
        while (number > 0) {
            ans += cnt[number % 10];
            number /= 10;
        }

        return ans;
    }
}
