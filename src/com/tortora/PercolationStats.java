package com.tortora;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private double stddev;
    private double trials;
    private final double[] thresholdArray;
    private static final double confidence95 = 1.96;
    private final double elapsedTime;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        Stopwatch stopwatch = new Stopwatch();
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n or trials must be greater than 0");
        }

        this.trials = trials;
        thresholdArray = new double[trials];
        System.out.println(n);

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                percolation.open(x, y);
                System.out.println(x + " " + y);
            }
            double threshold = (double) percolation.numberOfOpenSites() / (n * n);
            thresholdArray[i] = threshold;
        }
        elapsedTime = stopwatch.elapsedTime();


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
        return mean() - ((confidence95 * stddev) / Math.floor(Math.sqrt(trials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((confidence95 * stddev) / Math.floor(Math.sqrt(trials)));
    }

    // test client (see below)
    public static void main(String[] args) {

        PercolationStats percolationStats = new PercolationStats(20,1);


        /*PercolationStats percolationStats = new PercolationStats(200,100);
        System.out.println("mean                        = " + percolationStats.mean());
        System.out.println("stddev                      = " + percolationStats.stddev());
        System.out.println("95% confidence interval     = [" + percolationStats.confidenceLo() +
                ", " + percolationStats.confidenceHi() + "]");
        System.out.println("Algorithm runtime           = " + percolationStats.elapsedTime);
        System.out.println();
        percolationStats = new PercolationStats(200,100);
        System.out.println("mean                        = " + percolationStats.mean());
        System.out.println("stddev                      = " + percolationStats.stddev());
        System.out.println("95% confidence interval     = [" + percolationStats.confidenceLo() +
                ", " + percolationStats.confidenceHi() + "]");
        System.out.println("Algorithm runtime           = " + percolationStats.elapsedTime);
        System.out.println();
        percolationStats = new PercolationStats(2,10000);
        System.out.println("mean                        = " + percolationStats.mean());
        System.out.println("stddev                      = " + percolationStats.stddev());
        System.out.println("95% confidence interval     = [" + percolationStats.confidenceLo() +
                ", " + percolationStats.confidenceHi() + "]");
        System.out.println("Algorithm runtime           = " + percolationStats.elapsedTime);
        System.out.println();
        percolationStats = new PercolationStats(2,100000);
        System.out.println("mean                        = " + percolationStats.mean());
        System.out.println("stddev                      = " + percolationStats.stddev());
        System.out.println("95% confidence interval     = [" + percolationStats.confidenceLo() +
                ", " + percolationStats.confidenceHi() + "]");
        System.out.println("Algorithm runtime           = " + percolationStats.elapsedTime);
     */
    }
}
