import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public void add(T x) {
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size ++;
    }

    public boolean contains(T x) {
        for (int i = 0; i< size; i ++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    private class ArraySetIterator<T> implements Iterator<T>{
        private int wizPos;

        public  ArraySetIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = (T) items[wizPos];
            wizPos++;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator<>();
    }

    @Override
    public String toString() {
        StringBuilder x = new StringBuilder();
        x.append("(");
        for (T i : this) {
            x.append(i);
            x.append(" ");
        }
        x.append(")");
        return x.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArraySet otherArraySet) { //get a new arrayset called otherArraySet
            if (this.size != otherArraySet.size) {
                return false;
            }
            for (T i : this) {
                if (!otherArraySet.contains(i))
                    return false;
            }
            return true;
        }
        return false; // o is not an arrayset
    }

    public static void main(String[] args) {
        ArraySet<Integer> S = new ArraySet<>();
        S.add(5);
        S.add(23);
        S.add(42);

        System.out.println(S.contains(42));
        System.out.println(S.contains(50));

        Iterator<Integer> seer = S.iterator();
        while (seer.hasNext()) {
            int x= seer.next();
            System.out.println(x);
        }

        for (int i : S) {
            System.out.println(i);
        }

        System.out.println(S.toString());

        ArraySet<Integer> S2 = new ArraySet<>();
        S2.add(5);
        S2.add(23);
        S2.add(42);
        System.out.println(S2.equals(S));
    }
}