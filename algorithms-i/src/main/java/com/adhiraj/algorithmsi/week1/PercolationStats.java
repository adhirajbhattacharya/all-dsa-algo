package com.adhiraj.algorithmsi.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE_CONSTANT = 1.96D;
  private final double[] percolates;
  private double mean;
  private double stddev;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("Illegal size or no. of trials");
    }
    percolates = new double[trials];
    while (trials > 0) {
      trials--;
      Percolation percolation = new Percolation(n);
      mean = Double.NEGATIVE_INFINITY;
      stddev = Double.NEGATIVE_INFINITY;
      do {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        if (percolation.isOpen(row, col)) {
          continue;
        }
        percolation.open(row, col);
      } while (!percolation.percolates());
      percolates[trials] = 1D * percolation.numberOfOpenSites() / (n * n);
    }
  }

  public double mean() {
    if (mean == Double.NEGATIVE_INFINITY) {
      mean = StdStats.mean(percolates);
    }
    return mean;
  }

  public double stddev() {
    if (stddev == Double.NEGATIVE_INFINITY) {
      stddev = StdStats.stddev(percolates);
    }
    return stddev;
  }

  public double confidenceLo() {
    return mean() - CONFIDENCE_CONSTANT * stddev() / Math.sqrt(percolates.length);
  }

  public double confidenceHi() {
    return mean() + CONFIDENCE_CONSTANT * stddev() / Math.sqrt(percolates.length);
  }

  public static void main(String[] args) {
    PercolationStats stats =
        new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println(
        "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
  }

}
