package com.adhiraj.dsaalgo.leetcode.week5;

// Simple prefix and suffix heights on left and right of each point. Get water at point by difference with the min barrier on either side.
// HAS 1 ALT SOLUTION. Uses O(1) space.
public class Lc42 {
    public int trap(int[] height) {

        int[] prefix = new int[height.length], suffix = new int[height.length];
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            prefix[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            suffix[i] = max;
            max = Math.max(max, height[i]);
        }

        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int minbarrier = Math.min(prefix[i], suffix[i]);
            if (height[i] < minbarrier)
                water += minbarrier - height[i];
        }

        return water;
    }
}

class Lc42Alt {
    public int trap(int[] height) {

        int i = 0, j = height.length - 1, leftbarrier = height[i], rightbarrier = height[j], water = 0;

        while (i <= j) {
            if (leftbarrier < rightbarrier) {
                int h = height[i];
                if (h < leftbarrier) {
                    water += leftbarrier - h;
                } else {
                    leftbarrier = h;
                }
                i++;
            } else {
                int h = height[j];
                if (h < rightbarrier) {
                    water += rightbarrier - h;
                } else {
                    rightbarrier = h;
                }
                j--;
            }
        }

        return water;
    }
}