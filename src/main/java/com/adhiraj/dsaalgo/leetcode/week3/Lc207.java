package com.adhiraj.dsaalgo.leetcode.week3;

import com.adhiraj.dsaalgo.leetcode.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Uses dfs to check if graph has cycle. If yes then not DAG. Topo-sort not possible.
// This one uses visited and recursion array. See alternate using only single int array for tracking.
// Can be done by Kahn's algo also. see below
// HAS 2 ALT SOLUTION
public class Lc207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 || prerequisites.length < 2) return true;

        return isDag(numCourses, new Graph(prerequisites, numCourses));
    }

    private boolean isDag(int countOfNodes, Graph graph) {
        // instead of keep 2 separate arrays for current recursion and visited,
        // OR we can use a single int array with where we can use

        boolean[] recursion = new boolean[countOfNodes];
        boolean[] visited = new boolean[countOfNodes];

        for (int i = 0; i < countOfNodes; i++) {
            if (hasCycleDfs(graph, recursion, visited, i)) return false;
        }
        return true;
    }

    private boolean hasCycleDfs(Graph graph, boolean [] recursion, boolean[] visited, int source) {
        if (recursion[source])
            return true;

        if (visited[source])
            return false;
        visited[source] = true;

        recursion[source] = true;
        for (int i : graph.vertices[source]) {
            if (hasCycleDfs(graph, recursion, visited, i)) return true;
        }
        recursion[source] = false;

        return false;
    }
}

// using a single int array for dfs
class Lc207Alt {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 || prerequisites.length < 2) return true;

        return isDag(numCourses, new Graph(prerequisites, numCourses));
    }

    private boolean isDag(int countOfNodes, Graph graph) {
        // instead of keep 2 separate arrays for current recursion and visited,
        // OR we can use a single int array with where we can use

        int[] nodetracker = new int[countOfNodes];

        for (int i = 0; i < countOfNodes; i++) {
            if (hasCycleDfs(graph, nodetracker, i)) return false;
        }
        return true;
    }

    private boolean hasCycleDfs(Graph graph, int[] nodetracker, int source) {
        if (nodetracker[source] == 2) // dfs on node already completed before
            return false;

        if (nodetracker[source] == 1) // node was previously visited on this current recursion
            return true;

        nodetracker[source] = 1; // mark node visited in current recursion
        for (int i : graph.vertices[source]) {
            if (hasCycleDfs(graph, nodetracker, i)) return true;
        }

        nodetracker[source] = 2; // mark node as recursion completed without any cycle

        return false;
    }
}

// Using Kahn's algo -- Precondition: for toposort, atleast one node with 0 indegree must be there
// --> Create graph with indegrees at each vertice. --> Start BFS with all vertices having zero indegrees
// --> At each iteration add node to sorted list, Remove the indegrees from each adjacent node, When indegree of any node reduces to zero, add to BFS queue
// --> After processing if any nodes left still with indegree, Toposort not possible, else return the sorted list
class Lc207AltKahn {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 || prerequisites.length < 2) return true;
        return !topoSort(numCourses, new Graph(prerequisites, numCourses)).isEmpty(); // kahn's
    }

    public List<Integer> topoSort(int countOfNodes, Graph g) {
        List<Integer> res = new ArrayList<>();

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < countOfNodes; i++) {
            if (g.indegrees[i] == 0)
                q.offer(i);
        }

        while (!q.isEmpty()) {
            int src = q.poll();
            res.add(src);
            for (int i : g.vertices[src]) {
                g.indegrees[i]--;
                if (g.indegrees[i] == 0)
                    q.offer(i);
            }
        }

        for (int i = 0; i < countOfNodes; i++) {
            if (g.indegrees[i] > 0)
                return new ArrayList<>();
        }

        return res;
    }
}