package com.adhiraj.dsaalgo.leetcode.week8;

// similar to rainwater trapping
public class Lc11{
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;

        int maxwater = 0;

        while (left < right) {
            int curr = Math.min(height[left], height[right]) * (right - left);
            maxwater = Math.max(maxwater, curr);
            if (height[left] > height[right])
                right--;
            else //if (height[left] <= height[right]) -- it does not matter which is done in case of left==right
                left++;
        }
        return maxwater;
    }
}
