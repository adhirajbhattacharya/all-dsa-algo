package com.adhiraj.algorithmsi.week3.mergesort;

import java.util.Comparator;
import com.adhiraj.algorithmsi.week2.elementarysorts.InsertionSort;

public class MergeSort {

  private MergeSort() {}

  private static final int ARRAY_LENGTH_INSERTION_SORT = 3;

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void sort(T[] a) {
    T[] aux = (T[]) new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
  }

  @SuppressWarnings("unchecked")
  public static <T> void sort(T[] a, Comparator<T> c) {
    T[] aux = (T[]) new Comparable[a.length];
    sort(a, aux, 0, a.length - 1, c);
  }

  private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
    if (hi <= lo + ARRAY_LENGTH_INSERTION_SORT - 1) {
      InsertionSort.sort(a, lo, hi);
      return;
    }
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    if (compare(a[mid], a[mid + 1]) == -1) {
      return;
    }
    merge(a, aux, lo, mid, hi);
  }

  private static <T> void sort(T[] a, T[] aux, int lo, int hi, Comparator<T> c) {
    if (hi <= lo + ARRAY_LENGTH_INSERTION_SORT - 1) {
      InsertionSort.sort(a, lo, hi, c);
      return;
    }
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid, c);
    sort(a, aux, mid + 1, hi, c);
    if (compare(a[mid], a[mid + 1], c) == -1) {
      return;
    }
    merge(a, aux, lo, mid, hi, c);

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
    Integer[] a = {2, 5, 8, 9, 0, 1, 3};// , 2, 7, 3, 2, 0
    MergeSort.sort(a);
    show(a);
  }
}
