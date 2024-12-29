import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class Node {
        private T data;
        private Node prev;
        private Node next;
    }

    public Node sentinel = new Node();
    public int size = 0;

    public LinkedListDeque61B() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T x) {
        Node n = new Node();
        n.next = sentinel.next;
        this.sentinel.next = n;
        n.data = x;
        n.prev = sentinel;
        n.next.prev = n;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node p = new Node();
        p.prev = sentinel.prev;
        this.sentinel.prev = p;
        p.data = x;
        p.next = sentinel;
        p.prev.next = p;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node head = sentinel;
        for (int i = 0; i < this.size(); i++) {
            head = head.next;
            list.add(head.data);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = sentinel;
        size--;
        return null;
    }

    @Override
    public T removeLast() {
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = sentinel;
        size--;
        return null;
    }

    @Override
    public T get(int index) {
        Node p = sentinel;
        for (int i = 0; i < index + 1; i++) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
