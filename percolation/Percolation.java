/* *****************************************************************************
 *  Name:              Dane Balia
 *  Last modified:     03/20/2020
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class Percolation {

        private int arraySize = 0;
        private int n;
        private int openSites = 0;

        private int[] state;
        private List<Integer> fullsites = new ArrayList<>();
        private int lastKnownRow = 0;
        private int lastKnownColumn = 0;

        // creates n-by-n grid, with all sites initially blocked
        public Percolation(int inputSize) {
                if (n < 0) throw new IllegalArgumentException();

                this.n = inputSize + 1;
                arraySize = n * n;
                state = new int[arraySize];
        }

        // opens the site (row, col) if it is not open already
        public void open(int row, int col) {
                if (row > n || col > n) throw new IllegalArgumentException();
                lastKnownRow = row;
                lastKnownColumn = col;
                state[n * row + col] = 1;
        }

        // is the site (row, col) open?
        public boolean isOpen(int row, int col) {
                if (row > n || col > n) throw new IllegalArgumentException();
                openSites++;
                return state[n * row + col] == 1;
        }

        // is the site (row, col) full?
        public boolean isFull(int row, int col) {
                if (row > n || col > n) throw new IllegalArgumentException();

                boolean itIsFull = false;

                // Top Row
                if(row == 1 && isOpen(row, col)) {
                        fullsites.add(n * row + col);
                        itIsFull = true;
                }

                // Second Row Down
                if(row > 1 && isOpen(row, col)) {
                        boolean top = isAdjacentAFull(row - 1, col);
                        boolean left = isAdjacentAFull(row, col - 1);
                        boolean right = isAdjacentAFull(row, col + 1);

                        if (top || left || right) itIsFull = true;

                        fullsites.add(n * row + col);
                }

                return itIsFull;

        }

        private boolean isAdjacentAFull(int row, int col) {
                return fullsites.contains(n * row + col);
        }

        // returns the number of open sites
        public int numberOfOpenSites() {
            return openSites;
        }

        // does the system percolate?
        public boolean percolates() {
                return isFull(lastKnownRow, lastKnownColumn);
        }

        // test client (optional)
        public static void main(String[] args) {
              Percolation percolation = new Percolation(5);
        }
}
