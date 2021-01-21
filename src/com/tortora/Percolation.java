package com.tortora;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF wqu;
    private final boolean[] grid;
    private int openSites = 0;
    private final int gridSize;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        gridSize = (n * n);
        wqu = new WeightedQuickUnionUF(gridSize + 2);
        grid = new boolean[gridSize];
        for (int i = 0; i < gridSize; i++){
            grid[i] = false;
        }
        for (int i = 1; i <= n; i++){
            wqu.union(0,i);
        }
        for (int i = n * n; i > (n*n) - n; i-- ){
            wqu.union(gridSize + 1, i);
        }
    }

    // opens the site (row, col) if it is not open already
    // union inputs need extra index due to offset of arrays from virtual points
    public void open(int row, int col) {
        validateIndex(row,col);
            if (isFull(row, col)) {
                grid[xyTo1D(row, col)] = true;
                openSites++;
            }
        if (withinGrid(row - 1,col - 1)) {
            if (grid[xyTo1D(row - 1, col - 1)]) {
                wqu.union(xyTo1D(row, col) + 1, xyTo1D(row - 1, col - 1) + 1);
            }
        }
        if (withinGrid(row - 1,col )) {
            if (grid[xyTo1D(row - 1, col)]) {
                        wqu.union(xyTo1D(row, col) + 1,xyTo1D(row - 1, col) + 1);
                    }
        }
        if (withinGrid(row - 1,col + 1)) {
            if (grid[xyTo1D(row - 1, col + 1)]) {
                        wqu.union(xyTo1D(row, col) + 1,xyTo1D(row - 1, col + 1) + 1);
                    }
        }
        if (withinGrid(row ,col - 1)) {
            if (grid[xyTo1D(row, col - 1)]) {
                        wqu.union(xyTo1D(row, col) + 1,xyTo1D(row, col - 1) + 1);
                    }
        }
        if (withinGrid(row ,col +1 )) {
            if (grid[xyTo1D(row, col + 1)]) {
                        wqu.union(xyTo1D(row, col) + 1,xyTo1D(row, col + 1) + 1);
                    }
        }
        if (withinGrid(row + 1,col  - 1)) {
            if (grid[xyTo1D(row + 1, col - 1)]) {
                        wqu.union(xyTo1D(row, col) + 1,xyTo1D(row + 1, col - 1) + 1);
                    }
        }
        if (withinGrid(row + 1, col)) {
            if (grid[xyTo1D(row + 1, col)]) {
                        wqu.union(xyTo1D(row, col) + 1,xyTo1D(row + 1, col) + 1);
                    }
        }
        if (withinGrid(row + 1, col + 1)) {
            if (grid[xyTo1D(row + 1, col + 1)]) {
                        wqu.union(xyTo1D(row,col) + 1,xyTo1D(row + 1, col + 1) + 1);
                    }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return grid[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        return !grid[xyTo1D(row, col)];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqu.connected(0, gridSize + 1);
    }

    private int xyTo1D(int x, int y){
        return (int)((Math.floor(Math.sqrt(gridSize)) * x + y));
    }

    private void validateIndex(int x, int y) throws ArrayIndexOutOfBoundsException{
        int i = xyTo1D(x,y);
        if (i < 0 || i >= grid.length){
            throw new ArrayIndexOutOfBoundsException("row index i out of bounds");
        }
    }

    private boolean withinGrid(int x, int y){
        int i = xyTo1D(x,y);
        return i >= 0 && i < grid.length;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}

