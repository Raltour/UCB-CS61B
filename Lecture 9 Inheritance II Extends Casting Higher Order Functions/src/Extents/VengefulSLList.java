package Extents;

public class VengefulSLList<Item> extends SLList<Item> {

    SLList<Item> lostItems;

    public VengefulSLList() {
        lostItems = new SLList<>();
    }

    public VengefulSLList(Item x) {
        super(x);
        lostItems = new SLList<>();
    }

    public void printLostItems() {
        lostItems.print();
    }

    @Override
    public Item removeLast () {
        Item x = super.removeLast();
        lostItems.addLast(x);
        return x;
    }

    public static void main(String[] args) {
        VengefulSLList<Integer> vs1 = new VengefulSLList<>(0);
        vs1.addLast(1);
        vs1.addLast(5);
        vs1.addLast(10);
        vs1.addLast(13);

        vs1.removeLast();
        vs1.removeLast();

        vs1.printLostItems();
        vs1.print();
    }
}