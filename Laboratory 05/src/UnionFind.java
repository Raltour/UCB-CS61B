public class UnionFind {
    // TODO: Instance variables
    private int[] items;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        items = new int[N];
        for (int i = 0; i < N; i++) {
            items[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int index = v;
        while (items[index] >= 0) {
            index = items[index];
        }
        return -1 * items[index];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return items[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= items.length) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        int index = v;
        while (items[index] >= 0) {
            index = items[index];
        }
        int move = v;
        int last = move;
        while (items[move] >= 0) {
            move = items[move];
            items[last] = index;
            last = move;
        }
        return index;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (!connected(v1, v2)) {
            int indv1 = v1;
            int indv2 = v2;
            while (items[indv1] >= 0) {
                indv1 = items[indv1];
            }
            while (items[indv2] >= 0) {
                indv2 = items[indv2];
            }

            if (items[indv1] >= items[indv2]) {
                items[indv2] += items[indv1];
                items[indv1] = indv2;
            }
            else {
                items[indv1] += items[indv2];
                items[indv2] = indv1;
            }
        }
    }

}
