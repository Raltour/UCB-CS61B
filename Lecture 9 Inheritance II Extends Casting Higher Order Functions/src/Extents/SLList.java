package Extents;

public class SLList<Item> implements List<Item>{
    private class IntNode {
        public Item item;
        public IntNode next;

        public IntNode(Item i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;
    private int size;

    //Creates a new Extents.SLList with one item, namely x.
    public SLList() {
        first = new IntNode(null, null);
        size = 0;
    }

    public SLList(Item x) {
        first = new IntNode(null, null);
        first.next = new IntNode(x, null);
        size = 1;
    }

    @Override
    //Adds item x to the front of the list.
    public void addFirst(Item x) {
        this.first.next = new IntNode(x, first.next);
        size++;
    }

    @Override
    //Add x to the end of the list.
    public void addLast(Item x) {
        IntNode p = this.first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size++;
    }

    @Override
    public void insert(Item x, int position) {

    }

    @Override
    public Item get(int position) {
        IntNode p = first;
        for (int i = 0; i < position; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Item removeLast() {
        IntNode p = this.first;
        while (p.next.next != null) {
            p = p.next;
        }
        Item r = p.next.item;
        p.next = null;
        size--;
        return r;
    }

    public Item getLast() {
        IntNode p = this.first;
        while (p.next.next != null) {
            p = p.next;
        }
        return p.item;
    }

}