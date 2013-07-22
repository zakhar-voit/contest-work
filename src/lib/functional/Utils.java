package lib.functional;

import lib.functional.wrappers.Processing;

/**
 * Some functions specific for functional programming.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class Utils {
    @SuppressWarnings("unchecked")
    public static <Arg, Res> Res[] map(Arg[] array, Processing<Arg, Res> function) {
        Res[] res = (Res[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = function.call(array[i]);
        }
        return res;
    }
}
