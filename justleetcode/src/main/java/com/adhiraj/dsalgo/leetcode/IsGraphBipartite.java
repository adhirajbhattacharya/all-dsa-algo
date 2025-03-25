package com.adhiraj.dsalgo.leetcode;

import java.util.Arrays;

public class IsGraphBipartite {
    public static void main(String[] args) {
        System.out.println(new IsGraphBipartite().isBipartite(new int[][] {{1}, {0, 3}, {3}, {1, 2}}));
    }

    public boolean isBipartite(int[][] graph) {
        return isTwoColoringPossible(graph);
    }

    private boolean isTwoColoringPossible(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        boolean isTwoColoringPossible = true;
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) continue;
            isTwoColoringPossible = isTwoColoringPossible && twoColoringUtil(graph, i, colors, 1);
            if (!isTwoColoringPossible) return false;
        }
        return true;
    }

    private boolean twoColoringUtil(int[][] graph, int vertex, int[] colors, int color) {
        if (colors[vertex] != 0 && colors[vertex] != color) return false;
        if (colors[vertex] != 0) return true;
        colors[vertex] = color;
        for (int next : graph[vertex]) {
            if (!twoColoringUtil(graph, next, colors, 3 - color)) return false;
        }
        return true;
    }

    // m-coloring is polynomial will not give best case for bipartite. good only for chromatic number
    private boolean isMcoloringPossible(int[][] graph, int m) {
        int n = graph.length;
        int[] colors = new int[n];
        Arrays.fill(colors, -1);
        return mColoringUtil(graph, 0, colors, m);
    }

    private boolean mColoringUtil(int[][] graph, int vertex, int[] colors, int m) {
        if (vertex == graph.length) return true;
        if (colors[vertex] != -1) return true;
        for (int i = 0; i < m; i++) {
            boolean isSafe = isSafeColoring(graph, vertex, colors, i);
            if (!isSafe) continue;
            colors[vertex] = i;
            isSafe = mColoringUtil(graph, vertex + 1, colors, m);
            if (isSafe) return true;
            colors[vertex] = -1;
        }
        return false;
    }

    private boolean isSafeColoring(int[][] graph, int vertex, int[] colors, int color) {
        int[] adj = graph[vertex];
        int n = adj.length;
        for (int j : adj) {
            if (colors[j] == color) return false;
        }
        return true;
    }
}
