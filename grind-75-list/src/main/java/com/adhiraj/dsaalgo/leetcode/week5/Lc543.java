package com.adhiraj.dsaalgo.leetcode.week5;

import com.adhiraj.dsaalgo.leetcode.Pair;
import com.adhiraj.dsaalgo.leetcode.TreeNode;

public class Lc543 {
    public int diameterOfBinaryTree(TreeNode root) {

        Pair longPaths = getLongestPaths(root);

        return longPaths.second - 1;
    }

    public Pair getLongestPaths(TreeNode node) {
        if (node == null) return new Pair(0, 0);

        Pair leftPaths = getLongestPaths(node.left);
        Pair rightPaths = getLongestPaths(node.right);

        int maxHeightFromNode = Math.max(leftPaths.first, rightPaths.first) + 1;
        int maxPathTillNow = Math.max(leftPaths.first + rightPaths.first + 1, leftPaths.second);
        maxPathTillNow = Math.max(maxPathTillNow, rightPaths.second);

        return new Pair(maxHeightFromNode, maxPathTillNow);
    }
}
