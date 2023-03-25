package com.adhiraj.algorithmsi.week2.elementarysorts;

import java.util.Comparator;

public class InsertionSort {

  private InsertionSort() {}

  public static <T extends Comparable<T>> void sort(T[] a) {
    sort(a, 0, a.length - 1);
  }

  public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {

    for (int i = lo + 1; i <= hi; i++) {
      for (int j = i; j > lo; j--) {
        if (compare(a[j - 1], a[j]) == 1) {
          swap(a, j - 1, j);
        }
      }
    }
  }

  public static <T> void sort(T[] a, Comparator<T> c) {
    sort(a, 0, a.length - 1, c);
  }

  public static <T> void sort(T[] a, int lo, int hi, Comparator<T> c) {
  
    for (int i = lo + 1; i <= hi; i++) {
      for (int j = i; j > lo; j--) {
        if (compare(a[j - 1], a[j], c) == 1) {
          swap(a, j - 1, j);
        }
      }
    }
  }

  private static <T extends Comparable<T>> int compare(T t1, T t2) {
    return t1.compareTo(t2);
  }

  private static <T> int compare(T t1, T t2, Comparator<T> c) {
    return c.compare(t1, t2);
  }

  private static <T> void swap(T[] a, int i, int j) {
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
    InsertionSort.sort(a);
    show(a);
  }
}
