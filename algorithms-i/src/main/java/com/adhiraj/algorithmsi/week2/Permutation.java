package com.adhiraj.algorithmsi.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    RandomizedQueue<String> sub = new RandomizedQueue<>();

    while (!StdIn.isEmpty()) {
      sub.enqueue(StdIn.readString());
    }

    while (n > 0) {
      n--;
      StdOut.println(sub.dequeue());
    }
  }
}
