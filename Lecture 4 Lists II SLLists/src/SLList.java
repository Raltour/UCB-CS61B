public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;

    //Creates a new SLList with one item, namely x.
    public SLList (int x) {
        first = new IntNode(x, null);
    }

    //Adds item x to the front of the list.
    public void addFirst(int x) {
        this.first = new IntNode(x, first);
    }

    //Gets the first item in the list.
    public int getFirst() {
        return this.first.item;
    }

    //Add x to the end of the list.
    public void addLast(int x) {
        IntNode p = this.first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    //Add x to the end of the list using recursion.
    public void addLast_(int x) {
        IntNode p = this.first;
        if (p.next != null) {
            SLList A = new SLList(x);
            A.first = p.next;
            A.addLast_(x);
        } else {
            p.next = new IntNode(x, null);
        }
    }

    //Gets the last item of the list.
    public int getLast() {
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        return p.item;
    }

    //Gets the last item of the list using recusion.
    public int getLast_() {
        IntNode p = first;
        if (p.next == null)
            return p.item;
        p = p.next;
        SLList T = new SLList(1);
        T.first = p;
        return T.getLast_();
    }

    //Returns the size of the list.
    public int size() {
        return size(this.first);
    }

    //Returns the size of the list, starting at IntNode p.
    private static int size(IntNode p) {
        if (p.next == null)
            return 1;
        return (1 + size(p.next));
    }

    public static void main(String[] args) {
        SLList L = new SLList(5);
        L.addFirst(10);
        System.out.println(L.getFirst());
        L.addLast(15);
        System.out.println(L.getLast());
        L.addLast_(20);
        System.out.println(L.getLast_());
        System.out.println(L.size());
    }
}