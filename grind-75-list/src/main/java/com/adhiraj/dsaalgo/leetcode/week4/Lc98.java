package com.adhiraj.dsaalgo.leetcode.week4;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

// Logical solution
// Can also be done by creating inorder representation but may be more space, although time order same
public class Lc98 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) return true;

        if (root.val > max || root.val < min)
            return false;

        if (root.val == max && root.right != null)
            return false;
        if (root.val == min && root.left != null)
            return false;

        return isValidBST(root.left, min, root.val - 1) && isValidBST(root.right, root.val + 1, max);
    }
}
