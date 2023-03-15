package com.adhiraj.dsaalgo.leetcode.week1;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

public class Lc235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == root || q == root)
            return root;
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);;
        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}