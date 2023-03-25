package com.adhiraj.algorithmsi.week3.mergesort;

import java.util.Comparator;

public class BottomUpMergeSort {

  private BottomUpMergeSort() {}

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void sort(T[] a) {
    int hi = a.length - 1;
    T[] aux = (T[]) new Comparable[a.length];
    for (int sz = 1; sz <= hi; sz += sz) {
      for (int lo = 0; lo <= hi - sz; lo += sz + sz) {
        merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, hi));
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> void sort(T[] a, Comparator<T> c) {
    int hi = a.length - 1;
    T[] aux = (T[]) new Object[a.length];
    for (int sz = 1; sz <= hi; sz += sz) {
      for (int lo = 0; lo <= hi - sz; lo += sz + sz) {
        merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, hi), c);
      }
    }
  }

  private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
    for (int i = lo; i <= hi; i++) {
      aux[i] = a[i];
    }
    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (compare(aux[j], aux[i]) == -1) {
        a[k] = aux[j++];
      } else {
        a[k] = aux[i++];
      }
    }
  }

  private static <T> void merge(T[] a, T[] aux, int lo, int mid, int hi, Comparator<T> c) {
    for (int i = lo; i <= hi; i++) {
      aux[i] = a[i];
    }
    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (compare(aux[j], aux[i], c) == -1) {
        a[k] = aux[j++];
      } else {
        a[k] = aux[i++];
      }
    }
  }

  private static <T extends Comparable<T>> int compare(T t1, T t2) {
    return t1.compareTo(t2);
  }

  private static <T> int compare(T t1, T t2, Comparator<T> c) {
    return c.compare(t1, t2);
  }
  
  private static <T> void show(T[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  public static void main(String[] args) {
    Integer[] a = {2, 5, 8, 9, 0, 1, 3, 2, 7, 3, 2, 0};
    BottomUpMergeSort.sort(a);
    show(a);
  }

}
