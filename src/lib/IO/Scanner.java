package lib.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

/**
 * Created with Intellij Idea
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */

public class Scanner {
    BufferedReader in;
    StringTokenizer tok;

    public Scanner(InputStream in) {
        this.in = new BufferedReader(new InputStreamReader(in));
        tok = new StringTokenizer("");
    }

    public String nextToken() {
        if (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(nextLine());
            return nextToken();
        }

        return tok.nextToken();
    }

    private String tryReadNextLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new InputMismatchException();
        }
    }

    public String nextLine() {
        String newLine = tryReadNextLine();
        if (newLine == null)
            throw new InputMismatchException();
        return newLine;
    }

    public int nextInt() {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() {
        return Double.parseDouble(nextToken());
    }

    public boolean EOF() {
        while (!tok.hasMoreTokens()) {
            String newLine = tryReadNextLine();
            if (newLine == null)
                return true;
            tok = new StringTokenizer(newLine);
        }
        return false;
    }

}
