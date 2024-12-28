package GenericLists;

public class SLList<cheese> {
    private class IntNode {
        public cheese item;
        public IntNode next;

        public IntNode(cheese i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;
    public int size;

    //Creates a new SLList with one item, namely x.
    public SLList (cheese x) {
        first = new IntNode(null, null);
        size = 0;
    }

    //Adds item x to the front of the list.
    public void addFirst(cheese x) {
        this.first = new IntNode(x, first);
        size++;
    }

    //Gets the first item in the list.
    public cheese getFirst() {
        return this.first.item;
    }

    //Add x to the end of the list.
    public void addLast(cheese x) {
        IntNode p = this.first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    //Gets the last item of the list.
    public cheese getLast() {
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        return p.item;
    }

    public static void main(String[] args) {
        SLList<String> L = new SLList<String>("what");
        L.addLast("the");
        L.addLast("dog");
        L.addLast("doin");
        System.out.println(L.getLast());
    }
}