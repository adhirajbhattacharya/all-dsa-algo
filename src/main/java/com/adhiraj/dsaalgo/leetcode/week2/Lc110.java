package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

// alternate 1 solution calculates depth at each level, does not look good. can use hashmap for memory but still performance not good
// alternate 2 would be best as there is no extra ds required
public class Lc110 {
    public boolean isBalanced(TreeNode root) {
        return isBalanced(root, new MutableInt(0));
    }

    private boolean isBalanced(TreeNode root, MutableInt height) {
        if (root == null)
            return true;

        MutableInt lh = new MutableInt(0), rh = new MutableInt(0);

        boolean l = isBalanced(root.left, lh);
        boolean r = isBalanced(root.right, rh);

        if (Math.abs(lh.value - rh.value) > 1) {
            return false;
        }

        height.value = 1 + Math.max(lh.value, rh.value);

        return l && r;
    }
}

class MutableInt {
    int value;

    public MutableInt(int value) {
        this.value = value;
    }
}

class Lc110Alt1 {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        int heightDiff = lh > rh ? lh - rh : rh - lh;

        if (heightDiff > 1)
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root) {
        if (root == null) return 0;

        int lh = getHeight(root.left);
        int rh = getHeight(root.right);

        return lh > rh ? 1 + lh : 1 + rh;
    }
}

// best solution
class Lc110Alt2 {
    public boolean isBalanced(TreeNode root) {
        return isBalanced2(root) != -1;
    }

    private int isBalanced2 (TreeNode root){
        if (root == null){
            return 0;
        }
        int left = isBalanced2(root.left);
        if (left == -1){
            return -1;
        }
        int right = isBalanced2(root.right);
        if (right == -1){
            return -1;
        }

        if (right - left >= -1 && right - left <= 1){
            return Math.max(left, right) + 1;
        }
        return -1;
    }
}
