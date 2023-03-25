package com.adhiraj.algorithmsi.week2.elementarysorts;

import java.util.Comparator;

public class SelectionSort {

  private SelectionSort() {}

  public static <T extends Comparable<T>> void sort(T[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < a.length; j++) {
        if (compare(a[min], a[j]) == 1) {
          min = j;
        }
      }
      swap(a, i, min);
    }
  }

  public static <T> void sort(T[] a, Comparator<T> c) {
    for (int i = 0; i < a.length - 1; i++) {
      int min = i;
      for (int j = i + 1; j < a.length; j++) {
        if (compare(a[min], a[j], c) == 1) {
          min = j;
        }
      }
      swap(a, i, min);
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
    Integer[] a = {2, 5, 8, 9, 0, 1, 3, 2, 7, 3, 2};
    SelectionSort.sort(a);
    show(a);
  }

}
