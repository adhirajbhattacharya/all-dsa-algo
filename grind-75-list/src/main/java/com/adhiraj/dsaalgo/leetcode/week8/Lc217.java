package com.adhiraj.dsaalgo.leetcode.week8;

import java.util.HashSet;
import java.util.Set;

public class Lc217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!seen.add(nums[i])) return true;
        }
        return false;
    }
}