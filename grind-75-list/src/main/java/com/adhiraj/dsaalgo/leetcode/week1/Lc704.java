package com.adhiraj.dsaalgo.leetcode.week1;

public class Lc704 {
    public int search(int[] nums, int target) {

        int lo = 0, hi = nums.length - 1;

        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            int midVal = nums[mid];
            if (midVal > target)
                hi = mid - 1;
            else if (midVal < target)
                lo = mid + 1;
            else
                return mid;
        }

        return -1;
    }
}