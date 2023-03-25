package com.adhiraj.dsaalgo.leetcode.week8;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

public class Lc105 {
    public static void main(String[] args) {
        new Lc105().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        return buildTree(preorder, inorder, 0, 0, n - 1, n);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int prIdx, int inSt, int inEn, int size) {
        if (size <= 0 || size != inEn - inSt + 1 || prIdx < 0 || prIdx >= size || inSt < 0 || inEn >= preorder.length
                || inSt > inEn) {
            return null;
        }

        int curr = preorder[prIdx];
        TreeNode node = new TreeNode(curr);
        int inIdx = findIdxInorder(inorder, curr, inSt, inEn);
        int leftSize = inIdx - inSt;
        int rightSize = inEn - inIdx;

        node.left = buildTree(preorder, inorder, prIdx + 1, inSt, inIdx - 1, leftSize);
        node.right = buildTree(preorder, inorder, prIdx + leftSize + 1, inIdx + 1, inEn, rightSize);

        return node;
    }

    private int findIdxInorder(int[] inorder, int curr, int inSt, int inEn) {
        for (int i = inSt; i <= inEn; i++) {
            if (inorder[i] == curr)
                return i;
        }

        return inSt;
    }
}