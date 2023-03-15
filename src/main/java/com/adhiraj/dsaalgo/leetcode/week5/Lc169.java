package com.adhiraj.dsaalgo.leetcode.week5;

import java.util.Arrays;

// HAS 1 ALTERNATE. Logically both are same
public class Lc169 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int i = 1, majority = nums[0], maxcount = 1, count = 1, curr = nums[0];

        while (i < n) {
            int ele = nums[i];
            if (majority == ele) {
                count++;
                majority++;
            } else if (curr == ele) {
                count++;
            } else {
                curr = ele;
                count = 1;
            }

            if (maxcount < count) {
                majority = curr;
                maxcount = count;
            }
            i++;
        }

        return majority;
    }
}

class Lc169Alt {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int i = 0, majority = nums[0], maxcount = 1;

        while (i < n) {
            int count = 0, ele = nums[i];
            while (i < n && nums[i] == ele) {
                i++;
                count++;
            }
            if (maxcount < count) {
                majority = ele;
                maxcount = count;
            }
        }

        return majority;
    }
}