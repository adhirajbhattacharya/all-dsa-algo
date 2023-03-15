package com.adhiraj.dsaalgo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this form adjacency list graph will be used when you dont have number of nodes known before hand.
// integer mapping to nodes not required
public class GraphWithMap {
    public Map<String, List<String>> adj;

    public GraphWithMap() {
        adj = new HashMap<>();
    }

    public void init(String s) {
        if (adj.containsKey(s))
            return;
        adj.put(s, new ArrayList<>());
    }

    public void addEdge(String s1, String s2) {
        List<String> nodes = adj.get(s1);
        if (nodes == null) {
            nodes = new ArrayList<>();
            adj.put(s1, nodes);
        }
        nodes.add(s2);

        nodes = adj.get(s2);
        if (nodes == null) {
            nodes = new ArrayList<>();
            adj.put(s2, nodes);
        }
        nodes.add(s1);
    }
}