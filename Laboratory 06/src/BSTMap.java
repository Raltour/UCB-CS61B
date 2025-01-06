import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
    }


    private Node<K, V> root;
    private int size;

    public BSTMap() {
        root = new Node<>();
        size = 0;
    }


    @Override
    public void put(K key, V value) {
        if (value == null && key == null) {
            return;
        }
        if (!this.containsKey(key)) {
            size++;
        }
        if (root.key == null) {
            this.root.key = key;
            this.root.value = value;
            this.root.left = new Node<>();
            this.root.right = new Node<>();
        } else if (this.root.key.equals(key)) {
            this.root.value = value;
        } else {
            BSTMap next = new BSTMap();
            if (key.compareTo(root.key) < 0) {
                next.root = this.root.left;
                next.put(key, value);
            } else {
                next.root = this.root.right;
                next.put(key, value);
            }
        }
    }

    @Override
    public V get(K key) {
        if (this.containsKey(key)) {
            if (root.key.equals(key)) {
                return root.value;
            } else {
                BSTMap next = new BSTMap();
                if (key.compareTo(root.key) < 0) {
                    next.root = this.root.left;
                    return (V) next.get(key);

                } else {
                    next.root = this.root.right;
                    return (V) next.get(key);
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (this.root.key == null) {
            return false;
        } else if (root.key.equals(key)) {
            return true;
        } else {
            BSTMap next = new BSTMap();
            if (key.compareTo(root.key) < 0) {
                next.root = this.root.left;
                return next.containsKey(key);
            } else {
                next.root = this.root.right;
                return next.containsKey(key);
            }
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        size = 0;
        this.root = new Node<>();
    }

    @Override
    public Set<K> keySet() {
        Set s = new TreeSet<>();
        if (this.root.key != null) {
            if (this.root.left != null) {
                BSTMap next = new BSTMap();
                next.root = this.root.left;
                s.addAll(next.keySet());
            }
            s.add(this.root.key);
            if (this.root.right != null) {
                BSTMap next = new BSTMap();
                next.root = this.root.right;
                s.addAll(next.keySet());
            }
        }
        return s;
    }

    @Override
    public V remove(K key) {
        if (!this.containsKey(key)) {
            return null;
        } else if (!this.root.key.equals(key)) {
            V value = get(key);
            BSTMap help = new BSTMap();
            help = this.helpRemove(key);
            this.removeHelpInsert(help, this);
            this.size--;
            return value;
        } else {
            V value = get(key);
            BSTMap help = new BSTMap();
            help.root = this.root;
            this.root = new Node<>();
            this.root.left = help.root.left;
            this.root.right = help.root.right;
            help.root = this.root;
            BSTMap newMap = new BSTMap();
            this.removeHelpInsert(help, newMap);
            this.root = newMap.root;
            size--;
            return value;
        }
    }

    public BSTMap<K, V> helpRemove(K key) {
        if (root.key.equals(key)) {
            Node temp = root;
            root = new Node<>();
            root.left = temp.left;
            root.right = temp.right;
            return this;
        } else {
            BSTMap next = new BSTMap();
            if (key.compareTo(root.key) < 0) {
                next.root = this.root.left;
                return next.helpRemove(key);
            } else {
                next.root = this.root.right;
                return next.helpRemove(key);
            }
        }
    }

    public void removeHelpInsert(BSTMap<K, V> help, BSTMap<K, V> origin) {
        if (!(help.root.key == null)) {
            origin.put(help.root.key, help.root.value);
        }
        if (!(help.root.left == null)) {
            BSTMap left = new BSTMap();
            left.root = help.root.left;
            this.removeHelpInsert(left, origin);
        }
        if (!(help.root.right == null)) {
            BSTMap right = new BSTMap();
            right.root = help.root.right;
            this.removeHelpInsert(right, origin);
        }
    }


    private class MapIterator implements Iterator<K> {

        private int position;

        public MapIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size();
        }

        @Override
        public K next() {
            position++;
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MapIterator();
    }


    public void printInOrder() {
        if (this.root.key != null) {
            if (this.root.left != null) {
                BSTMap next = new BSTMap();
                next.root = this.root.left;
                next.printInOrder();
            }
            System.out.println("Key:" + this.root.key + " Value:" + this.root.value);
            if (this.root.right != null) {
                BSTMap next = new BSTMap();
                next.root = this.root.right;
                next.printInOrder();
            }
        }
    }

}
