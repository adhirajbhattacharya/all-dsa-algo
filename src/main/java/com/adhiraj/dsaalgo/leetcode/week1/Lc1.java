package com.adhiraj.dsaalgo.leetcode.week1;

import java.util.HashMap;
import java.util.Map;

public class Lc1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i];
            Integer j = indexMap.get(rem);
            if (j != null && i != j)
                return new int[] {i, j};
        }

        return new int[] {-1, -1};
    }
}