package com.adhiraj.dsaalgo.leetcode.week3;

import com.adhiraj.dsaalgo.leetcode.Node;

import java.util.HashMap;
import java.util.Map;

// Done with dfs recursion. Can also be done with bfs
public class Lc133 {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> clones = new HashMap<>();

        return cloneGraph(node, clones);
    }

    private Node cloneGraph(Node node, Map<Node, Node> clones) {
        Node clone = clones.get(node);
        if (clone != null)
            return clone;

        clone = new Node(node.val);
        clones.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor, clones));
        }

        return clone;
    }
}
