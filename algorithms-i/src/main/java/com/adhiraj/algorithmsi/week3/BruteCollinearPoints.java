package com.adhiraj.algorithmsi.week3;

import java.util.Arrays;
import edu.princeton.cs.algs4.LinkedBag;
import edu.princeton.cs.algs4.Quick3way;

public class BruteCollinearPoints {

  private final LineSegment[] segments;

  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }
    for (Point p : points) {
      if (p == null) {
        throw new IllegalArgumentException();
      }
    }
    Point[] copy = Arrays.copyOf(points, points.length);
    Quick3way.sort(copy);
    for (int i = 0; i < copy.length - 1; i++) {
      if (copy[i].compareTo(copy[i + 1]) == 0) {
        throw new IllegalArgumentException();
      }
    }
    LinkedBag<LineSegment> lines = findLines(copy);

    segments = new LineSegment[lines.size()];
    int i = 0;
    for (LineSegment line : lines) {
      segments[i++] = line;
    }
  }

  private LinkedBag<LineSegment> findLines(Point[] points) {
    LinkedBag<LineSegment> lines = new LinkedBag<>();
    for (int x = points.length - 1; x >= 0; x--) {
      for (int y = x - 1; y >= 0; y--) {
        for (int z = y - 1; z >= 0; z--) {
          for (int w = z - 1; w >= 0; w--) {
            double slopeij = points[x].slopeTo(points[y]);
            double slopejk = points[y].slopeTo(points[z]);
            double slopekl = points[z].slopeTo(points[w]);
            if (Double.compare(slopeij, slopejk) == 0 && Double.compare(slopejk, slopekl) == 0) {
              lines.add(new LineSegment(points[x], points[w]));
            }
          }
        }
      }
    }
    return lines;
  }

  public int numberOfSegments() {
    return segments.length;
  }

  public LineSegment[] segments() {
    return Arrays.copyOf(segments, segments.length);
  }

  public static void main(String[] args) {
    // optional test client
  }
}
