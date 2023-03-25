package com.adhiraj.dsaalgo.leetcode.week5;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

public class Lc297 {

    int idx;

    // Encodes a tree to a single string. (preorder with null tracking)
    public String serialize(TreeNode root) {
        StringBuilder preorder = new StringBuilder();
        dfsPreorder(root, preorder);
        return preorder.substring(0, preorder.length() - 1);
    }

    private void dfsPreorder(TreeNode node, StringBuilder preorder) {
        if (node == null) {
            preorder.append("X$");
            return;
        }

        preorder.append(node.val).append("$");
        dfsPreorder(node.left, preorder);
        dfsPreorder(node.right, preorder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("X".equals(data)) return null;

        idx = 0;
        String[] preorder = data.split("\\$");
        TreeNode root = createTree(preorder);
        return root;
    }

    private TreeNode createTree(String[] preorder) {
        if (idx >= preorder.length) return null;
        String data = preorder[idx++];
        if ("X".equals(data)) return null;

        TreeNode root = new TreeNode(Integer.parseInt(data));
        root.left = createTree(preorder);
        root.right = createTree(preorder);

        return root;
    }
}
