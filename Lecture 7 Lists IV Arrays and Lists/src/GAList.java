public class GAList<Glorp> {
    private Glorp[] items;
    private int size;

    public GAList() {
        items = (Glorp[]) new Object[8];
        size = 0;
    }

    private void resize(int cap) {
        Glorp[] a = (Glorp[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public Glorp get(int i) {
        return items[i];
    }
}