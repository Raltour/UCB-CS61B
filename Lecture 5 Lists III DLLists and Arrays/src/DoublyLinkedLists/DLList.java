package DoublyLinkedLists;

public class DLList {

    public Note first;
    public Note last;
    public int size;

    public DLList() {
        first = new Note(null, 0, null);
        last = new Note(first, 0, null);
        first.next = last;
        int size = 0;
    }

    public void addfirst(int x) {
        this.first.next = new Note(first, x, first.next);
        this.first.next.next.prev = first.next;
        size ++;
    }

    public void addlast(int x) {
        this.last.prev = new Note(last.prev, x, last);
        this.last.prev.prev.next = last.prev;
        size ++;
    }

    public void removelast() {
        last.prev = last.prev.prev;
        last.prev.next = last;
        this.size--;
    }

    public void showDLList() {
        Note p = this.first.next;
        while (p.next != null) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        DLList L = new DLList();
        L.addlast(1);
        L.addlast(2);
        L.addfirst(11);
        L.addfirst(12);
        System.out.println(L.size);
        L.showDLList();
        L.removelast();
        L.showDLList();
        System.out.println(L.size);
    }
}