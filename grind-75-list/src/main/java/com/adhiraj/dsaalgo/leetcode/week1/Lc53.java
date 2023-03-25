package com.adhiraj.dsaalgo.leetcode.week1;

// this is not exactly kadane's
public class Lc53 {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, curr = 0;
        for (int i = 0; i < nums.length; i++) {
            curr += nums[i];
            if (curr > max)
                max = curr;
            if (curr < 0) {
                curr = 0;
            }
        }
        return max;
    }
}

class Lc53Kadane {
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        int leftidx = 0, maxsofar = Integer.MIN_VALUE, maxendinghere = 0,
                maxleft = -1, maxright = -1;
        for (int i = 0; i < size; i++) {
            maxendinghere += nums[i];
            if (maxendinghere > maxsofar) {
                maxsofar = maxendinghere;
                maxleft = leftidx;
                maxright = i;
            }

            if (maxendinghere <= 0) {
                maxendinghere = 0;
                leftidx = i + 1;
            }
        }

        return maxsofar;
    }
}