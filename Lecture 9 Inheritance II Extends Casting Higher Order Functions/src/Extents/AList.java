package Extents;

public class AList<Item> implements List<Item>{
    private Item[] items;
    private int size;

    public AList() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    private void resize(int cap) {
        Item[] a = (Item[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addFirst(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        for (int j = size; j > 0; j--) {
            items[j] = items[j -1];
        }
        items[0] = x;
    }

    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size++;
    }

    public void insert(Item x, int position) {
        if (size == items.length) {
            resize(size * 2);
        }
        for (int j = size; j > position; j--) {
            items[j] = items[j -1];
        }
        items[position - 1] = x;
    }

    public Item get(int i) {
        return items[i];
    }

    public int size(){
        return size;
    }

    public Item removeLast() {
        Item i = items[size - 1];
        items[size - 1] = null;
        size--;
        return i;
    }
}