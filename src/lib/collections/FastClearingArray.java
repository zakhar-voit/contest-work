package lib.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * Array that supports operation clear with O(1) asymptotic.
 *
 * @author Zakhar Voit(zakharvoit@gmail.com)
 */
@SuppressWarnings("unused")
public class FastClearingArray<T> implements Iterable<T>, RandomAccess, Cloneable {
    int timer = 0;
    final T defaultValue;
    T[] array;
    int[] lastUpdate;

    private FastClearingArray(T[] array, int[] lastUpdate, T defaultValue, int timer) {
        this.array = array.clone();
        this.lastUpdate = lastUpdate.clone();
        this.defaultValue = defaultValue;
        this.timer = timer;
    }

    @SuppressWarnings("unchecked")
    public FastClearingArray(int size, T defaultValue) {
        array = (T[]) new Object[size];
        lastUpdate = new int[size];
        this.defaultValue = defaultValue;
        clear();
    }

    public int getLength() {
        return array.length;
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= array.length)
            throw new ArrayIndexOutOfBoundsException(idx);
    }

    public T get(int idx) {
        checkIndex(idx);
        if (lastUpdate[idx] < timer) {
            return defaultValue;
        }
        return array[idx];
    }

    public void set(int idx, T value) {
        checkIndex(idx);
        lastUpdate[idx] = timer;
        array[idx] = value;
    }

    public void clear() {
        ++timer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos = 0;

            public boolean hasNext() {
                return pos < getLength();
            }

            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return get(pos++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    @SuppressWarnings("all")
    protected Object clone() throws CloneNotSupportedException {
        return new FastClearingArray<T>(array, lastUpdate, defaultValue, timer);
    }
}
