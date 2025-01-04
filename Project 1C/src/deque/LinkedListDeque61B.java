package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{

    private class Node {
        private T data;
        private Node prev;
        private Node next;
    }

    private Node sentinel = new Node();
    private int size = 0;

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
        Node n = sentinel.next;
        this.sentinel.next = n.next;
        this.sentinel.next.prev = sentinel;
        size--;
        return n.data;
    }

    @Override
    public T removeLast() {
        Node n = sentinel.prev;
        this.sentinel.prev = n.prev;
        this.sentinel.prev.next = sentinel;
        size--;
        return n.data;
    }

    @Override
    public T get(int index) {
        if (index >= this.size()) {
            return null;
        }
        Node p = sentinel;
        for (int i = 0; i < index + 1; i++) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= this.size()) {
            return null;
        }
        if (index == 0) {
            return this.sentinel.next.data;
        }
        LinkedListDeque61B<T> list = new LinkedListDeque61B<>();
        list.sentinel.next = this.sentinel.next.next;
        list.sentinel.prev = this.sentinel.prev;
        list.size = this.size() - 1;
        return list.getRecursive(index - 1);
    }


    private class LinkedListIterator<T> implements Iterator<T> {
        private int position;

        public LinkedListIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T result = (T) get(position);
            position++;
            return result;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>();
    }


    @Override
    public boolean equals(Object list) {
        if (this == list) { return true; }
        if (list instanceof LinkedListDeque61B<?>) {
            if (this.size() != ((LinkedListDeque61B<?>) list).size()) { return false; }
            for (int i = 0; i < this.size(); i++) {
                if (!(this.get(i) == ((LinkedListDeque61B) list).get(i))) { return false; }
            }
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return this.toList().toString();
    }
}
