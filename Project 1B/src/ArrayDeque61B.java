import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[2];
        size = 0;
        capacity = 2;
        nextFirst = 0;
        nextLast = 1;
    }

    public void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        System.arraycopy(items, nextFirst + 1,
                newItems, 0,
                capacity - nextFirst - 1);
        System.arraycopy(items, 0,
                newItems, capacity - nextFirst - 1,
                nextLast);
        capacity = newCapacity;
        nextFirst = capacity - 1;
        nextLast = size;
        items = newItems;
    }

    @Override
    public void addFirst(T x) {
        if (nextFirst == nextLast) {
            resize(capacity * 2);
        }
        items[nextFirst] = x;
        size++;
        nextFirst = Math.floorMod(nextFirst - 1, capacity);
    }

    @Override
    public void addLast(T x) {
        if (nextFirst == nextLast) {
            resize(capacity * 2);
        }
        items[nextLast] = x;
        size++;
        nextLast = Math.floorMod(nextLast + 1, capacity);
    }

    @Override
    public List toList() {
        List<T> list = new ArrayList<>();
        int i = nextFirst + 1;
        while (i != nextLast) {
            if (i == capacity) {
                i = 0;
            }
            list.add(items[i]);
            i++;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        nextFirst = Math.floorMod(nextFirst + 1, capacity);
        size--;
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        nextLast = Math.floorMod(nextLast - 1, capacity);
        size--;
        return items[nextLast];
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index + 1, capacity)];
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return items[Math.floorMod(nextFirst + 1, capacity)];
        }
        ArrayDeque61B<T> list = new ArrayDeque61B<T>();
        list.resize(capacity);
        System.arraycopy(this.items, 0, list.items, 0, capacity);
        list.nextFirst = Math.floorMod(this.nextFirst + 1, capacity);
        list.capacity = this.capacity;
        list.size = this.size - 1;
        return (T) list.getRecursive(index - 1);
    }
}
