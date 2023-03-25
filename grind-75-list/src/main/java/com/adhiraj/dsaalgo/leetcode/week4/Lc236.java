package com.adhiraj.dsaalgo.leetcode.week4;

import com.adhiraj.dsaalgo.leetcode.TreeNode;

import java.util.*;

// this is the best. will return null if either node is missing in tree
// Has 3 ALTERNATE SOLUTION. BFS and DFS one works properly also. But Alt will give result even when one node is not in tree.
public class Lc236 {

    boolean v1 = false, v2 = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = lcaUtil(root, p, q);
        if (v1 && v2)
            return lca;

        return null;
    }

    public TreeNode lcaUtil(TreeNode root, TreeNode p, TreeNode q) {
        // p, q are not in this recursion
        if (root == null)
            return null;

        TreeNode lca = null;

        // p is found
        if (root.val == p.val) {
            lca = root;
            v1 = true;
        }

        // q is found
        if (root.val == q.val) {
            lca = root;
            v2 = true;
        }

        TreeNode left = lcaUtil(root.left, p, q);
        TreeNode right = lcaUtil(root.right, p, q);

        // first node was found, but other would be marked as well since it did left and right recursion above
        if (lca != null)
            return lca;

        // both nodes found on either side of this node. above lca returned for both left and right each.
        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }
}

// Will not always work. When one node is null
class Lc236Alt {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;

        return left == null ? right : left;
    }
}

// DFS and BFS take 3 traversal. 1st solution only one.
class Lc236AltDfs {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayDeque<TreeNode> pathp = getPathDfs(root, p);
        ArrayDeque<TreeNode> pathq = getPathDfs(root, q);

        if (pathp == null || pathq == null) return null;
        TreeNode ret = null;
        while (!pathp.isEmpty() && !pathq.isEmpty() && pathp.peekLast().val == pathq.peekLast().val) {
            ret = pathp.pollLast();
            pathq.pollLast();
        }

        return ret;
    }

    public ArrayDeque<TreeNode> getPathDfs(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        ArrayDeque<TreeNode> res = new ArrayDeque<>();

        if (root.val == p.val) {
            res.offer(root);
            return res;
        }

        res = getPathDfs(root.left, p);
        if (res == null) res = getPathDfs(root.right, p);
        if (res == null) return null;

        res.offer(root);
        return res;
    }
}

class Lc236AltBfs {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        boolean foundp = false, foundq = false;

        Queue<TreeNode> queue = new ArrayDeque<>();
        Map<TreeNode, TreeNode> mapToParent = new HashMap<>();

        mapToParent.put(root, null);
        queue.offer(root);

        while (!queue.isEmpty() && (!foundp || !foundq)) {
            TreeNode curr = queue.poll();
            TreeNode left = curr.left;
            TreeNode right = curr.right;
            if (left != null) {
                if (left == p) foundp = true;
                else if (left == q) foundq = true;

                mapToParent.put(left, curr);
                queue.offer(left);
            }
            if (right != null) {
                if (right == p) foundp = true;
                else if (right == q) foundq = true;

                mapToParent.put(right, curr);
                queue.offer(right);
            }
        }

        Deque<TreeNode> pathToP = new LinkedList<>(), pathToQ = new LinkedList<>();

        TreeNode curr = p;
        while (curr != null) {
            pathToP.push(curr);
            curr = mapToParent.get(curr);
        }

        curr = q;
        while (curr != null) {
            pathToQ.push(curr);
            curr = mapToParent.get(curr);
        }

        curr = pathToP.pop();
        TreeNode curr1 = pathToQ.pop(), lca;

        lca = curr;

        while (curr == curr1) {
            lca = curr;
            if (!pathToP.isEmpty()) curr = pathToP.pop();
            if (!pathToQ.isEmpty()) curr1 = pathToQ.pop();
        }

        return lca;

    }
}
