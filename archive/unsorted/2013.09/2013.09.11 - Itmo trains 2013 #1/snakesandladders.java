package tasks;

import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class snakesandladders {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        final int FIELD_SIZE = 100;

        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        int[] go = new int[FIELD_SIZE];
        Arrays.fill(go, -1);

        int[] pos = new int[a];

        for (int i = 0; i < b; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            go[from] = to;
        }

        int currentPlayer = 0;
        for (int i = 0; i < c; i++) {
            int rolledNumber = in.nextInt();

            int newPos = pos[currentPlayer] + rolledNumber;
            if (newPos > FIELD_SIZE - 1)
                newPos = FIELD_SIZE - 1;

            while (go[newPos] != -1) {
                newPos = go[newPos];
            }

            pos[currentPlayer] = newPos;

            if (newPos == FIELD_SIZE - 1)
                break;

            currentPlayer = (currentPlayer + 1) % a;
        }

        for (int i = 0; i < a; i++) {
            out.println("Position of player " + (i + 1) + " is " + (pos[i] + 1) + ".");
        }
    }
}
