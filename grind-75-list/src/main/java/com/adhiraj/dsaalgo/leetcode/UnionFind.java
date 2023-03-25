package com.adhiraj.dsaalgo.leetcode;

public class UnionFind {
    int[] parent;
    int[] sz;
    int count;

    public UnionFind(int n) {
        parent = new int[n];
        sz = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int key) {
        int p = parent[key];
        while (p != parent[parent[p]]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        parent[key] = p;
        return p;
    }

    public void union(int k1, int k2) {
        int p1 = find(k1);
        int p2 = find(k2);
        if (p1 == p2) return;
        if (sz[p1] > sz[p2]) {
            sz[p1] += sz[p2];
            parent[k2] = p1;
        } else {
            sz[p2] += sz[p1];
            parent[k1] = p2;
        }
        count--;
    }
}
