package com.adhiraj.dsaalgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class DiGraph {
    public int vertices;
    public List<Integer>[] adj;
    public int[] indegrees;
    public int[] outdegrees;

    public DiGraph(int countOfNodes, int[][] edges) {
        this.vertices = countOfNodes;
        adj = new List[countOfNodes];
        indegrees = new int[countOfNodes];
        outdegrees = new int[countOfNodes];

        for (int i = 0; i < countOfNodes; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int src = edge[1];
            int dest = edge[0];
            addEdge(src, dest);
        }
    }

    protected void addEdge(int src, int dest) {
        adj[src].add(dest);
        indegrees[dest]++;
        outdegrees[src]++;
    }
}
