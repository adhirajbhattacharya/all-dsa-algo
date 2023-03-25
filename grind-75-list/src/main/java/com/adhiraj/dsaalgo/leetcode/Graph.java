package com.adhiraj.dsaalgo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List<Integer>[] vertices;
    public int[] indegrees;

    public Graph(int[][] edges, int countOfNodes) {
        vertices = (List<Integer>[]) new List[countOfNodes];
        indegrees = new int[countOfNodes];

        for (int i = 0; i < countOfNodes; i++) {
            vertices[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][1];
            int dest = edges[i][0];
            vertices[src].add(dest);
            indegrees[dest]++;
        }
    }
}
