package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            if (i > 0  && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int value = nums[i] + nums[j] + nums[k];
                if (value == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }

                if (value > 0) {
                    k--;
                } else {
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                }
            }
        }

        return res;
    }
}
