import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    public int openNumber;
    public WeightedQuickUnionUF ufT;
    public WeightedQuickUnionUF ufTB;
    public int[] isOpen;
    public int size;
    public int top;
    public int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("hehe");
        }
        openNumber = 0;
        ufT = new WeightedQuickUnionUF(N * N + 1);
        ufTB = new WeightedQuickUnionUF(N * N + 2);
        isOpen = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            isOpen[i] = -1;
        }
        size = N;
        top = N * N;
        bottom = N * N + 1;
        setTopAndBottom();
    }

    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("haha");
        }
        if (isOpen(row, col + 1)) {
            ufT.union(row * size + col, row * size + col + 1);
            ufTB.union(row * size + col, row * size + col + 1);
        }
        if (isOpen(row, col - 1)) {
            ufT.union(row * size + col, row * size + col - 1);
            ufTB.union(row * size + col, row * size + col - 1);
        }
        if (isOpen(row + 1, col)) {
            ufT.union(row * size + col, (row + 1) * size + col);
            ufTB.union(row * size + col, (row + 1) * size + col);
        }
        if (isOpen(row - 1, col)) {
            ufT.union(row * size + col, (row - 1) * size + col);
            ufTB.union(row * size + col, (row - 1) * size + col);
        }

        isOpen[row * size + col] = 1;
        openNumber++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        } else if (isOpen[row * size + col] == -1) {
            return false;
        }
        return true;
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        return ufT.connected(top, row * size + col);
    }

    public int numberOfOpenSites() {
        return openNumber;
    }

    public boolean percolates() {
        return ufTB.connected(top, bottom);
    }

    public void setTopAndBottom() {
        for (int i = 0; i < size; i++) {
            ufTB.union(i, top);
            ufTB.union((size - 1) * size + i, bottom);
            ufT.union(i, top);
        }
    }
}
