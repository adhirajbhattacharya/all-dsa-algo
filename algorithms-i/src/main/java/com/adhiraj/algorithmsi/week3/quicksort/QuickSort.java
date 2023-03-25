package com.adhiraj.algorithmsi.week3.quicksort;

import java.util.Comparator;
import com.adhiraj.algorithmsi.week2.elementarysorts.InsertionSort;
import com.adhiraj.algorithmsi.week2.elementarysorts.Shuffle;

public class QuickSort {

  private QuickSort() {}

  private static final int ARRAY_LENGTH_INSERTION_SORT = 3;

  public static <T extends Comparable<T>> void sort(T[] a) {
    Shuffle.shuffle(a);
    sort(a, 0, a.length - 1);
  }

  public static <T> void sort(T[] a, Comparator<T> c) {
    Shuffle.shuffle(a);
    sort(a, 0, a.length - 1, c);
  }

  private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
    if (hi <= lo + ARRAY_LENGTH_INSERTION_SORT - 1) {
      InsertionSort.sort(a, lo, hi);
      return;
    }
    int pivot = partition(a, lo, hi);
    sort(a, lo, pivot - 1);
    sort(a, pivot + 1, hi);
  }

  private static <T> void sort(T[] a, int lo, int hi, Comparator<T> c) {
    if (hi <= lo + ARRAY_LENGTH_INSERTION_SORT - 1) {
      InsertionSort.sort(a, lo, hi, c);
      return;
    }
    int pivot = partition(a, lo, hi, c);
    sort(a, lo, pivot - 1, c);
    sort(a, pivot + 1, hi, c);
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

  private static <T> void show(T[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public static void main(String[] args) {
    Integer[] a = {2, 5, 8, 9, 0, 1, 3, 2, 7, 3, 2, 0};
    QuickSort.sort(a);
    show(a);
  }

}
