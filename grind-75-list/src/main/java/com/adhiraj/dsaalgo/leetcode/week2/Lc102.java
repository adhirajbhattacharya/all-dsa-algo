package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Lc102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        return getBfsOrder(root);
    }

    private List<List<Integer>> getBfsOrder(TreeNode node) {
        if (node == null) return new ArrayList<>();

        Queue<TreeNode> q = new ArrayDeque<>();

        q.offer(node);
        // de-limiter for level
        q.offer(new TreeNode(1001));

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.val == 1001) {
                result.add(level);
                level = new ArrayList<>();
                if (!q.isEmpty())
                    q.offer(curr);
            } else {
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
                level.add(curr.val);
            }
        }
        return result;
    }
}
