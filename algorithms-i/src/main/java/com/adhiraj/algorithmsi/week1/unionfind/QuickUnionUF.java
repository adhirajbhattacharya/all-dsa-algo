package com.adhiraj.algorithmsi.week1.unionfind;

public class QuickUnionUF {

  private int[] id;
  private int connectedSets;

  public QuickUnionUF(int n) {
    if (n < 2) {
      throw new IllegalArgumentException("Element count must be more than 1");
    }
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
    connectedSets = n;
  }

  public void union(int p, int q) {
    if (connected(p, q)) {
      return;
    }
    int rootp = find(p);
    int rootq = find(q);
    id[rootp] = rootq;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int find(int p) {
    validate(p);
    while (p != id[p]) {
      p = id[p];
    }
    return p;
  }

  public int count() {
    return connectedSets;
  }

  private void validate(int i) {
    if (i < 0 || i >= id.length) {
      throw new IllegalArgumentException("Index " + i + " is not between 0 and " + (id.length - 1));
    }
  }
}
