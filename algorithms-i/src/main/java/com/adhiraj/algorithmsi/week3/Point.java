package com.adhiraj.algorithmsi.week3;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void draw() {
    StdDraw.point(x, y);
  }

  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  public double slopeTo(Point that) {
    if (this.y == that.y) {
      if (this.x == that.x) {
        return Double.NEGATIVE_INFINITY;
      } else {
        return 0;
      }
    }
    if (this.x == that.x) {
      return Double.POSITIVE_INFINITY;
    }
    return 1D * (that.y - this.y) / (that.x - this.x);
  }

  public int compareTo(Point that) {
    if (this == that)
      return 0;
    if (this.y < that.y) {
      return -1;
    } else if (this.y > that.y) {
      return 1;
    } else if (this.x < that.x) {
      return -1;
    } else if (this.x > that.x) {
      return 1;
    } else {
      return 0;
    }
  }

  public Comparator<Point> slopeOrder() {
    return new SlopeComparator();
  }

  private class SlopeComparator implements Comparator<Point> {
    private SlopeComparator() {
      // empty constructor
    }

    public int compare(Point o1, Point o2) {
      double slope1 = slopeTo(o1);
      double slope2 = slopeTo(o2);
      return Double.compare(slope1, slope2);
    }

  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public static void main(String[] args) {
    // optional test client
  }
}
