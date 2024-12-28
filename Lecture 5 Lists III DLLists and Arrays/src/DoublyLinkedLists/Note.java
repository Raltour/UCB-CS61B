package DoublyLinkedLists;

public class Note {

    Note prev;
    int item;
    Note next;

    public Note(Note p, int i, Note n) {
        prev = p;
        item = i;
        next = n;
    }
}