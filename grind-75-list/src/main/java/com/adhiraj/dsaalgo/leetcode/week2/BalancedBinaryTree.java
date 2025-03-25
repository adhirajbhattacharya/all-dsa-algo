package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 110. Balanced Binary Tree
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * Input: root = []
 * Output: true
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */

/**
 * O(N) TIME & O(N) SPACE
 */
public class BalancedBinaryTree {
    Map<TreeNode, Integer> heightmapcache = new HashMap<>();

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftheight = getHeight(root.left);
        int rightheight = getHeight(root.right);

        if (Math.abs(leftheight - rightheight) > 1) return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;

        Integer nodeheight = heightmapcache.get(node);
        if (nodeheight != null) return nodeheight;

        int leftheight = getHeight(node.left);
        int rightheight = getHeight(node.right);

        nodeheight = 1 + Math.max(leftheight, rightheight);
        heightmapcache.put(node, nodeheight);

        return nodeheight;
    }
}
