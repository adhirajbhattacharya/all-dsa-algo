package com.adhiraj.algorithmsi.week4.heapsort;

import java.util.Comparator;

public class HeapSort {
  
  private HeapSort() {
    // only static sort methods
  }
  
  public static <T extends Comparable<T>> void sort(T[] a) {
    int n = a.length;
    for (int i = n / 2; i >= 1; i--) {
      sink(a, i, n);
    }
    while (n > 1) {
      exch(a, 1, n);
      sink(a, 1, --n);
    }
  }
  
  public static <T> void sort(T[] a, Comparator<T> c) {
    int n = a.length;
    for (int i = n / 2; i >= 1; i--) {
      sink(a, i, n, c);
    }
    while (n >= 1) {
      exch(a, 1, n);
      sink(a, 1, --n, c);
    }
  }
  
  private static <T extends Comparable<T>> void sink(T[] a, int i, int n) {
    while (2 * i <= n) {
      int j = 2 * i;
      if (j < n && compare(a, j, j + 1) < 0) {
        j++;
      }
      if (compare(a, j, i) <= 0) {
        break;
      }
      exch(a, j, i);
      i = j;
    }
  }
  
  private static <T extends Comparable<T>> int compare(T[] a, int j, int i) {
    return a[j - 1].compareTo(a[i - 1]);
  }
  
  private static <T> void sink(T[] a, int i, int n, Comparator<T> c) {
    while (2 * i <= n) {
      int j = 2 * i;
      if (j < n && compare(a, j, j + 1, c) < 0) {
        j++;
      }
      if (compare(a, j, i, c) <= 0) {
        break;
      }
      exch(a, j, i);
      i = j;
    }
  }
  
  private static <T> int compare(T[] a, int j, int i, Comparator<T> c) {
    return c.compare(a[j - 1], a[i - 1]);
  }
  
  private static <T> void exch(T[] a, int j, int i) {
    T tmp = a[j - 1];
    a[j - 1] = a[i - 1];
    a[i - 1] = tmp;
  }
  
}
