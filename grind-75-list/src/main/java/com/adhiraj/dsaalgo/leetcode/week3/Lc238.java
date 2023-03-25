package com.adhiraj.dsaalgo.leetcode.week3;

// initial solution that came to mind did not work

// Create a prefix & suffix array where arr[i] = product of all elements to i's left/right
// Result array will be product of prefix and suffix array at i
// Below solution does not use prefix & suffix array but logically same
public class Lc238 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = nums[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i];
        }

        int right = 1;

        for (int i = n - 1; i > 0; i--) {
            res[i] = res[i - 1] * right;
            right *= nums[i];
        }

        res[0] = right;

        return res;
    }
}