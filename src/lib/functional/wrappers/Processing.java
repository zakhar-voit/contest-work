package lib.functional.wrappers;

/**
 * Wrapper for functions, that receives Argument and returns Result.
 * <p/>
 * For example:
 * toInt :: String -> Integer
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
public interface Processing<Argument, Result> {
    public Result call(Argument arg);
}
