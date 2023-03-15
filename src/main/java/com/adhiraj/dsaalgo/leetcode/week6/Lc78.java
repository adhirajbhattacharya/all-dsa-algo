package com.adhiraj.dsaalgo.leetcode.week6;

import java.util.ArrayList;
import java.util.List;

// Recursion select current index or not select. since you can choose item only once
public class Lc78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsets(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void subsets(int[] nums, int idx, List<Integer> curr, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[idx]);
        subsets(nums, idx + 1, curr, res);
        curr.remove((Integer) nums[idx]);
        subsets(nums, idx + 1, curr, res);
    }
}
