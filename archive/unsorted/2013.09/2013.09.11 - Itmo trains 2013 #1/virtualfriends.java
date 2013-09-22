package tasks;



import lib.IO.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class virtualfriends {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        hash.clear();

        String[][] inp = new String[n][2];

        for (int i = 0; i < n; i++) {
            inp[i][0] = in.nextToken();
            inp[i][1] = in.nextToken();
            hash.put(inp[i][0], 2 * i);
            hash.put(inp[i][1], 2 * i + 1);
        }

        id = new int[2 * n + 10];
        Arrays.fill(id, -1);
        size = new int[2 * n + 10];

        for (int i = 0; i < n; i++) {
            int a = hash.get(inp[i][0]);
            int b = hash.get(inp[i][1]);

            if (id[a] == -1) {
                id[a] = a;
                size[a] = 1;
            }

            if (id[b] == -1) {
                id[b] = b;
                size[b] = 1;
            }

            a = getId(a);
            b = getId(b);

            int sizea = size[a];
            int sizeb = size[b];

            if (a == b) {
                out.println(sizea);
                continue;
            }

            if (sizea < sizeb) {
                id[b] = a;
                size[a] = sizea + sizeb;
            } else {
                id[a] = b;
                size[b] = sizea + sizeb;
            }

            out.println(sizea + sizeb);
        }
    }

    int getId(int a) {
        int parent = id[a];
        if (parent == a)
            return a;
        else {
            parent = getId(parent);
            id[a] = parent;
            return parent;
        }
    }

    Map<String, Integer> hash = new HashMap<>();
    int[] id;
    int[] size;
}
