package com.tortora;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.lang.reflect.Array;

public class PercolationStats {
    private double stddev;
    private final double trials;
    private final double[] thresholdArray;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n or trials must be greater than 0");
        }
        Percolation percolation = new Percolation(n);
        this.trials = trials;
        thresholdArray = new double[trials];

        for (int i = 0; i < trials; i++) {
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(0, n);
                int y = StdRandom.uniform(0, n);
                percolation.open(x, y);
            }
            double threshold = percolation.numberOfOpenSites() / percolation.getGridSize();
            thresholdArray[i] = threshold;
        }

        System.out.println("mean                        = " + mean());
        System.out.println("stddev                      = " + stddev());
        System.out.println("95% confidence interval     = [" + confidenceLo() +
                                                        ", " + confidenceHi() + "]");
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholdArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(thresholdArray);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200,100);
        System.out.println();
        percolationStats = new PercolationStats(200,100);
        System.out.println();
        percolationStats = new PercolationStats(2,10000);
        System.out.println();
        percolationStats = new PercolationStats(2,100000);

    }
}
