package com.adhiraj.algorithmsi.week2.elementarysorts;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {

  private Shuffle() {}

  public static <T> void shuffle(T[] a) {
    for (int i = 1; i < a.length; i++) {
      int random = StdRandom.uniform(i + 1);
      swap(a, i, random);
    }
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
    Integer[] a = new Integer[52];
    for (int i = 0; i < a.length; i++) {
      a[i] = i;
    }
    Shuffle.shuffle(a);
    show(a);
  }

}
