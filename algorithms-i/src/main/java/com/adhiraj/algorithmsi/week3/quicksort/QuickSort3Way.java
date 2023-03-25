package com.adhiraj.algorithmsi.week3.quicksort;

import java.util.Comparator;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort3Way {

  private QuickSort3Way() {}

  public static <T extends Comparable<T>> void sort(T[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  public static <T extends Comparable<T>> void sort(T[] a, Comparator<T> c) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1, c);
  }

  private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
    if (hi <= lo) {
      return;
    }
    int lt = lo;
    int gt = hi;
    int i = lo + 1;
    while (i <= gt) {
      int cmp = compare(a[i], a[lt]);
      if (cmp < 0) {
        exch(a, i, lt);
        i++;
        lt++;
      } else if (cmp > 0) {
        exch(a, i, gt);
        gt--;
      } else {
        i++;
      }
    }
    sort(a, lo, lt - 1);
    sort(a, gt + 1, hi);
  }

  private static <T> void sort(T[] a, int lo, int hi, Comparator<T> c) {
    int lt = lo;
    int gt = hi;
    int i = lo + 1;
    while (i <= gt) {
      int cmp = compare(a[lt], a[i], c);
      if (cmp < 1) {
        exch(a, i, lt);
        i++;
        lt++;
      } else if (cmp > 1) {
        exch(a, i, gt);
        gt--;
      } else {
        i++;
      }
    }
    sort(a, lo, lt - 1, c);
    sort(a, gt + 1, hi, c);
  }

  private static <T extends Comparable<T>> int compare(T t1, T t2) {
    return t1.compareTo(t2);
  }

  private static <T> int compare(T t1, T t2, Comparator<T> c) {
    return c.compare(t1, t2);
  }

  private static <T> void exch(T[] a, int i, int j) {
    T tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private static <T> void show(T[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public static void main(String[] args) {
    Integer[] a = {2, 5, 8, 9, 0, 1, 3, 2, 7, 3, 2, 0};
    QuickSort3Way.sort(a);
    show(a);
  }

}
