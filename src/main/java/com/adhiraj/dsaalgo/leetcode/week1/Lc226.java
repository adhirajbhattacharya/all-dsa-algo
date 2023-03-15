package com.adhiraj.dsaalgo.leetcode.week1;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

public class Lc226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}