package lib;

import lib.IO.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utilities that provides easy work with arrays.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class ArrayUtils {
    static public int[] toIntArray(Integer[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    static public int[] toIntArray(ArrayList<Integer> arr) {
        Integer[] res = new Integer[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return toIntArray(res);
    }

    static public int[] readIntArray(Scanner in, int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = in.nextInt();
        }
        return res;
    }

    static public int[] compressCoordinates(int[] all, int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int pos = Arrays.binarySearch(all, arr[i]);
            assert pos >= 0;
            res[i] = pos;
        }
        return res;
    }

    static public int[][] rotate(int[][] a) {
        if (a.length == 0)
            return a.clone();
        int[][] res = new int[a[0].length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                res[j][i] = a[i][j];
            }
        }
        return res;
    }
}
