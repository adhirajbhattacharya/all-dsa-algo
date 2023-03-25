package com.adhiraj.dsaalgo.leetcode.week6;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Unique dfs solution below. not done by me. found on LC
public class Lc199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(null);
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null && q.isEmpty()) break;
            else if (curr == null) {
                q.offer(curr);
                curr = q.poll();
                res.add(curr.val);
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
                continue;
            }

            res.set(res.size() - 1, curr.val);
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }

        return res;
    }
}

class Lc199AltDfs {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode root, int lvl, List<Integer> res) {
        if (root == null) return; // base case

        // at cur level: check if it's the 1st node from right
        if (res.size() == lvl) res.add(root.val);

        dfs(root.right, lvl + 1, res); // must go right first
        dfs(root.left , lvl + 1, res);
    }
}