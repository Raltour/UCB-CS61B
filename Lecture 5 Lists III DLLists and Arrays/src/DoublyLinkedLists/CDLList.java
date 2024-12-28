package DoublyLinkedLists;

public class CDLList {

    public Note sentinel;

    public CDLList() {
        sentinel = new Note(null, 0, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void showDLList() {
        Note p = this.sentinel.next;
        while (p.next != sentinel.next) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    public void addlast(int x) {
        sentinel.prev = new Note(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public void removelast() {
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
    }

    public static void main(String[] args) {
        CDLList L = new CDLList();
        L.addlast(5);
        L.addlast(10);
        L.showDLList();
        L.removelast();
        L.showDLList();
    }
}