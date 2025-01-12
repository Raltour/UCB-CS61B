package hashmap;

import net.sf.saxon.expr.Component;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Raltour
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int capacity;
    private double maxFactor;

    /** Constructors */
    public MyHashMap() {
        size = 0;
        capacity = 16;
        maxFactor = 0.75;
        buckets = (Collection<Node>[]) new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        size = 0;
        capacity = initialCapacity;
        maxFactor = 0.75;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        size = 0;
        capacity = initialCapacity;
        maxFactor = loadFactor;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    protected void resize() {
        if ( (double) size  / capacity >= maxFactor) {
            int newCapacity = capacity * 2;
            Collection<Node>[] newBuckets = (Collection<Node>[]) new Collection[newCapacity];
            for (int i = 0; i < newCapacity; i++) {
                newBuckets[i] = createBucket();
            }
            for (Collection<Node> bucket : buckets) {
                for (Node node : bucket) {
                    newBuckets[Math.floorMod(node.key.hashCode(), newCapacity)].add(node);
                }
            }
            buckets = newBuckets;
            capacity = newCapacity;
        }
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void put(K key, V value) {
        int index = Math.floorMod(key.hashCode(), capacity);
        if (containsKey(key)) {
            getNode(buckets[index], key).value = value;
        } else {
            buckets[index].add(new Node(key, value));
            size++;
            resize();
        }
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int index = Math.floorMod(key.hashCode(), capacity);
            return getNode(buckets[index], key).value;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), capacity);
       return getNode(buckets[index], key) != null;
    }

    public Node getNode(Collection<Node> col, K key) {
        for (Node node : col) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            Iterator<Node> ite = buckets[i].iterator();
            while (ite.hasNext()) {
                Node node = ite.next();
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), capacity);
        Node aim = getNode(buckets[index], key);
        if (aim != null) {
            size--;
            buckets[index].remove(aim);
            return aim.value;
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<K> {
        Iterator<K> ite;

        public MyIterator() {
            ite = keySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return ite.hasNext();
        }

        @Override
        public K next() {
            return ite.next();
        }
    }
}
