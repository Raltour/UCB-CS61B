/*
 * Weighted Quick Union Disjoint Sets
 * */


public class DisjointSets {

    private int[] id;

    public DisjointSets(int n) {
        this.id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = -1;
        }
    }

    public void printSets() {
        for (int j : id) {
            System.out.print(j + " ");
        }
    }

    public void connect(int a, int b) {
        if (!isConnected(a, b)) {
            int aa = a;
            int bb = b;
            while (id[aa] >= 0) {
                aa = id[aa];
            }
            while (id[bb] >= 0) {
                bb = id[bb];
            }

            if (id[aa] > id[bb]) {
                id[bb] += id[aa];
                id[aa] = bb;
            }
            else {
                id[aa] += id[bb];
                id[bb] = aa;
            }
        }
    }

    public boolean isConnected(int a, int b) {
        int aa = a;
        int bb = b;
        while (id[aa] >= 0) {
            aa = id[aa];
        }
        while (id[bb] >= 0) {
            bb = id[bb];
        }
        return aa == bb;
    }
}