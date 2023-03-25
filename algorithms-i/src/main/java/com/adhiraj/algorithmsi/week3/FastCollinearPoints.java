package com.adhiraj.algorithmsi.week3;

import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedBag;
import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

  private final LineSegment[] segments;

  public FastCollinearPoints(Point[] points) {
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
    Point[] aux = Arrays.copyOf(points, points.length);
    LinkedBag<LineSegment> lines = new LinkedBag<>();
    for (Point p : points) {
      Arrays.sort(aux, p.slopeOrder());

      int first = 1;
      int last = 1;
      while (first < aux.length) {
        last++;
        while (last < aux.length
            && Double.compare(p.slopeTo(aux[last]), p.slopeTo(aux[first])) == 0) {
          last++;
        }

        if (last - first > 2) {
          boolean flag = true;
          for (int i = first; i < last; i++) {
            if (p.compareTo(aux[i]) > 0) {
              flag = false;
            }
          }
          if (flag) {
            Point[] temp = Arrays.copyOfRange(aux, first, last);
            Arrays.sort(temp);
            lines.add(new LineSegment(p, temp[temp.length - 1]));
          }
        }
        first = last;
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
    // read the n points from a file
    In in = new In("collinear-testing/collinear/input400.txt");
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();
    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
}
