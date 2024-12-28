package SLListExercise;

public class SLList {

    IntNode first;

    public SLList() {
        first = new IntNode(0, null);
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void showSLList() {
        IntNode p = this.first;
        while (p.next != null) {
            System.out.println(p.item);
            p = p.next;
        }
    }

    public int sum() {
        IntNode p = this.first;
        int total = 0;

        while (p.next != null) {
            total += p.item;
            p = p.next;
        }

        return total;
    }

    public SLList even() {
        SLList Q = new SLList();
        IntNode p = this.first;

        while (p.next != null) {
            if (p.item %2 == 0) {
                Q.first = new IntNode(p.item, Q.first);
            }
            p = p.next;
        }
        return Q;
    }

    public static SLList common(SLList L1, SLList L2) {
        SLList C = new SLList();
        IntNode P1 = L1.first;
        IntNode P2 = L2.first;
        int ref;

        while (P1.next != null) {
            ref = 0;

            while(P2.next != null) {
                if (P1.item == P2.item) {
                    ref = 1;
                    break;
                }
                P2 = P2.next;
            }

            if (ref == 1)
                C.first = new IntNode(P1.item, C.first);

            P1 = P1.next;
            P2 = L2.first;
        }

        return C;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addFirst(5);
        L.addFirst(10);
        L.addFirst(15);
        L.addFirst(20);
        System.out.println(L.sum());

        SLList A = new SLList();
        A.addFirst(2);
        A.addFirst(3);
        A.addFirst(4);
        A.addFirst(5);
        A.even().showSLList();

        common(A, L).showSLList();
    }
}