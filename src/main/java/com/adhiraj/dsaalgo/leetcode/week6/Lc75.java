package com.adhiraj.dsaalgo.leetcode.week6;

public class Lc75 {
    public void sortColors(int[] nums) {
        int r = 0, w = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) r++;
            else if (nums[i] == 1) w++;
        }

        for (int i = 0; i < r ; i++) {
            nums[i] = 0;
        }
        for (int i = r; i < r + w ; i++) {
            nums[i] = 1;
        }
        for (int i = r + w; i < nums.length ; i++) {
            nums[i] = 2;
        }
    }
}