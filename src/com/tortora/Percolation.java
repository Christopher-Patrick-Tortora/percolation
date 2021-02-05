//TODO: Array isn't properly reading whats left and right of it,
// haven't checked up and down
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


        for (int i = 0; i < gridSize; i++){
            grid[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    // union inputs need extra index due to offset of arrays from virtual points
    public void open(int row, int col) {
        validateIndex(row, col);
        if (!isOpen(row, col)) {
            grid[xyTo1D(row, col)] = true;
            openSites++;

        }
        if (withinGrid(row - 1, col) && grid[xyTo1D(row - 1, col)]) {
            wqu.union(xyTo1D(row, col) + 1, xyTo1D(row - 1, col) + 1);
            System.out.println("up");
        }
        if (withinGrid(row, col + 1) && grid[xyTo1D(row, col + 1)]) {
            wqu.union(xyTo1D(row, col) + 1, xyTo1D(row, col + 1) + 1);
            System.out.println("right");
        }
        if (withinGrid(row, col - 1) && grid[xyTo1D(row, col - 1)]) {
            wqu.union(xyTo1D(row, col) + 1, xyTo1D(row, col - 1) + 1);
            System.out.println("left");
        }

        if (withinGrid(row + 1, col) && grid[xyTo1D(row + 1, col)]) {
            wqu.union(xyTo1D(row, col) + 1, xyTo1D(row + 1, col) + 1);
            System.out.println("down");
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
        return wqu.find(0) == wqu.find(xyTo1D(row , col) + 1) && isOpen(row, col);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqu.find(0) == wqu.find(gridSize + 1);
    }

    private int xyTo1D(int x, int y) {
        return (int) ((Math.floor(Math.sqrt(gridSize)) * (x-1) + y)) - 1;
    }

    private void validateIndex(int x, int y) {
        int i = xyTo1D(x, y);
        if (!withinGrid(x,y)) {
            throw new ArrayIndexOutOfBoundsException("row index " + i + " out of bounds " + gridSize);
        }

    }

    private boolean withinGrid(int x, int y) {
        int i = xyTo1D(x, y);
        return i >= 0 && i < grid.length;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);
        percolation.open(1,6);
        percolation.open(2,6);
        percolation.open(3,6);
        percolation.open(4,6);
        percolation.open(5,6);
        percolation.open(5,5);
        percolation.open(4,4);
        percolation.open(3,4);
        percolation.open(2,4);
        percolation.open(2,3);
        percolation.open(2,2);
        percolation.open(3,2);
        percolation.open(3,1);
        //percolation.open(2,1);
        /*System.out.println(percolation.grid[0]);
        System.out.println(percolation.wqu.find(1) == percolation.wqu.find(2));
        System.out.println(percolation.xyTo1D(1, 1));
        System.out.println(percolation.grid[percolation.xyTo1D(1, 1)]);
        */
        System.out.println(percolation.xyTo1D(-1,5));
        System.out.println(percolation.withinGrid(-1,5));
        percolation.validateIndex(-1,5);

    }
}

