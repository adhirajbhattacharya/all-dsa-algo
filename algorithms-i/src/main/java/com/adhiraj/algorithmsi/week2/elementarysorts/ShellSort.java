package com.adhiraj.algorithmsi.week2.elementarysorts;

import java.util.Comparator;

public class ShellSort {

  private ShellSort() {}

  public static <T extends Comparable<T>> void sort(T[] a) {
    int h = 1;
    while (h < a.length / 3) {
      h = 3 * h + 1;
    }

    do {
      for (int i = h; i < a.length; i++) {
        for (int j = i; j - h >= 0; j -= h) {
          if (compare(a[j - h], a[j]) == 1) {
            swap(a, j - h, j);
          }
        }
      }
      h /= 3;
    } while (h >= 1);
  }
  
  public static <T> void sort(T[] a, Comparator<T> c) {
    int h = 1;
    while (h < a.length / 3) {
      h = 3 * h + 1;
    }
    
    do {
      for (int i = h; i < a.length; i++) {
        for (int j = i; j - h >= 0; j -= h) {
          if (compare(a[j - h], a[j], c) == 1) {
            swap(a, j - h, j);
          }
        }
      }
      h /= 3;
    } while (h >= 1);
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
    ShellSort.sort(a);
    show(a);
  }
}
