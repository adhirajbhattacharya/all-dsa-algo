package com.adhiraj.dsaalgo.leetcode.week4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lc46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, new HashSet<>(), new ArrayList<>(), res);
        return res;
    }

    public void permute(int[] nums, Set<Integer> exclude, List<Integer> curr, List<List<Integer>> res) {

        if (exclude.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (exclude.contains(i)) continue;
            curr.add(nums[i]);
            exclude.add(i);
            permute(nums, exclude, curr, res);
            exclude.remove(i);
            curr.remove(curr.size() - 1);
        }
    }
}
