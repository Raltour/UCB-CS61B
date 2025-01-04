package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{

    public Comparator<T> comp;

    public MaxArrayDeque61B(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        int maxDex = 0;
        for (int i = 0; i < this.size(); i++) {
            int result = comp.compare(this.get(i), this.get(maxDex));
            if (result > 0) {
                maxDex = i;
            }
        }
        return this.get(maxDex);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        int maxDex = 0;
        for (int i = 0; i < this.size(); i++) {
            int result = c.compare(this.get(i), this.get(maxDex));
            if (result > 0) {
                maxDex = i;
            }
        }
        return this.get(maxDex);
    }

    private static class naturalOrderString implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static naturalOrderString getNaturalOrderString() {
        return new naturalOrderString();
    }
}
