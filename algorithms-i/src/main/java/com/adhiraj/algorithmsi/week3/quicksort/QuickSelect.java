package com.adhiraj.algorithmsi.week3.quicksort;

import java.util.Comparator;
import com.adhiraj.algorithmsi.week2.elementarysorts.Shuffle;

public class QuickSelect {

  private QuickSelect() {}

  public static <T extends Comparable<T>> T select(T[] a, int index) {
    if (index < 0 || index >= a.length) {
      throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + index);
    }
    Shuffle.shuffle(a);
    int lo = 0;
    int hi = a.length - 1;
    while (hi > lo) {
      int k = partition(a, lo, hi);
      if (index < k) {
        hi = k - 1;
      } else if (index > k) {
        lo = k + 1;
      } else {
        return a[k];
      }
    }
    return a[lo];
  }

  public static <T> T select(T[] a, int index, Comparator<T> c) {
    Shuffle.shuffle(a);
    int lo = 0;
    int hi = a.length - 1;
    while (hi > lo) {
      int k = partition(a, lo, hi, c);
      if (index < k) {
        hi = k - 1;
      } else if (index > k) {
        lo = k + 1;
      } else {
        return a[k];
      }
    }
    return a[index];
  }

  private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {

    int i = lo;
    int j = hi + 1;
    T pivot = a[lo];
    while (true) {
      while (compare(a[++i], pivot) < 0) {
        if (i == hi) {
          break;
        }
      }
      while (compare(pivot, a[--j]) < 0) {
        if (j == lo) {
          break;
        }
      }

      if (i >= j) {
        break;
      }
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  private static <T> int partition(T[] a, int lo, int hi, Comparator<T> c) {
  
    int i = lo;
    int j = hi + 1;
    T pivot = a[lo];
    while (true) {
      while (compare(a[++i], pivot, c) < 0) {
        if (i == hi) {
          break;
        }
      }
      while (compare(pivot, a[--j], c) < 0) {
        if (j == lo) {
          break;
        }
      }
  
      if (i >= j) {
        break;
      }
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
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

  public static void main(String[] args) {
    Integer[] a = {2, 5, 8, 9, 0, 1, 3, 2, 7, 3, 2, 0};
    for (int i = 0; i < a.length; i++) {
      Integer ith = (Integer) QuickSelect.select(a, i);
      System.out.println(ith);
    }
  }
}
