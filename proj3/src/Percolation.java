import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int N;
    private int virtualTop;
    private int virtualBottom;
    private int openSites = 0;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    public void open(int row, int col) {
        validate(row, col);

        if (!grid[row][col]) {
            openSites++;
        }

        grid[row][col] = true;

        int index = xyTo1D(row, col);
        if (row == 0) {
            uf.union(index, virtualTop);
            uf2.union(index, virtualTop);
        }
        if (row == N - 1) {
            uf.union(index, virtualBottom);
        }

        if (row > 0 && isOpen(row - 1, col)) {
            int index2 = xyTo1D(row - 1, col);
            uf.union(index2, index);
            uf2.union(index2, index);
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            int index2 = xyTo1D(row + 1, col);
            uf.union(index2, index);
            uf2.union(index2, index);
        }
        if (col > 0 && isOpen(row, col - 1)) {
            int index2 = xyTo1D(row, col - 1);
            uf.union(index2, index);
            uf2.union(index2, index);
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            int index2 = xyTo1D(row, col + 1);
            uf.union(index2, index);
            uf2.union(index2, index);
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row, col);
        return isOpen(row, col) && uf2.connected(index, virtualTop);
        // virtualTop serves as something that we can use to check if any index in the grid is
        // connected to an index in the top row (row 0) (that's the main element that water starts with) because
        // we are connecting the top row index to virtualTop in open()

        // checking if the square is connected to the virtualTop in the uf2 set that doesn't have
        // any connections to the virtualBottom from another uf set, therefore preventing backwash
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom); // check if both belong to the same set
    }

    // helper functions
    public int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public void validate(int row, int col) {
        if  (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

}
