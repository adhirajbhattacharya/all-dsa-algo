package com.adhiraj.algorithmsi.week1.unionfind;

public class WeightedQuickUnionUF {

  private int[] id;
  private int[] sz;
  private int connectedSets;

  public WeightedQuickUnionUF(int n) {
    if (n < 2) {
      throw new IllegalArgumentException("Element count must be more than 1");
    }
    id = new int[n];
    sz = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      sz[i] = 1;
    }
    connectedSets = n;
  }

  public void union(int p, int q) {
    int rootp = find(p);
    int rootq = find(q);
    if (rootp == rootq) {
      return;
    }
    if (sz[rootp] < sz[rootq]) {
      id[rootp] = rootq;
      sz[rootq]+=sz[rootp];
    } else {
      id[rootq] = rootp;
      sz[rootp]+=sz[rootq];
    }
    connectedSets--;
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
