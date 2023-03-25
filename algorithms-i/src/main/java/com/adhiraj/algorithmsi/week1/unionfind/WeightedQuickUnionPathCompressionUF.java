package com.adhiraj.algorithmsi.week1.unionfind;

public class WeightedQuickUnionPathCompressionUF {

  private int[] id;
  private int[] rank;
  private int connectedSets;

  public WeightedQuickUnionPathCompressionUF(int n) {
    if (n < 2) {
      throw new IllegalArgumentException("Element count must be more than 1");
    }
    id = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
      rank[i] = 0;
    }
    connectedSets = n;
  }

  public void union(int p, int q) {
    int rootp = find(p);
    int rootq = find(q);
    if (rootp == rootq) {
      return;
    }
    if (rank[rootp] < rank[rootq]) {
      id[rootp] = rootq;
    } else if (rank[rootq] < rank[rootp]){
      id[rootq] = rootp;
    } else {
      id[rootp] = rootq;
      rank[rootp]++;
    }
    connectedSets--;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int find(int p) {
    validate(p);
    while (p != id[p]) {
      id[p] = id[id[p]];
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
