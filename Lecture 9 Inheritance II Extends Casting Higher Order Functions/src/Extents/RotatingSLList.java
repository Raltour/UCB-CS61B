package Extents;

public class RotatingSLList<Item> extends SLList<Item>{

    public void rotateRight() {
        Item x = removeLast();
        addFirst(x);
    }

    public static void main(String[] args) {
        RotatingSLList<Integer> rsl = new RotatingSLList<>();
        rsl.addLast(10);
        rsl.addLast(11);
        rsl.addLast(12);
        rsl.addLast(13);

        rsl.rotateRight();
        rsl.print();
    }
}