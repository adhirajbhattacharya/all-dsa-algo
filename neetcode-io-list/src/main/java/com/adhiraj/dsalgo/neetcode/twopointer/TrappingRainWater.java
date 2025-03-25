package com.adhiraj.dsalgo.neetcode.twopointer;

public class TrappingRainWater {
    class Solution {
        public int trap(int[] height) {
            int l = 0, r = height.length - 1;
            int maxL = height[l], maxR = height[r];
            int water = 0;

            while (l != r) {
                System.out.println("---------------------------------------");
                System.out.println("maxL -> " + maxL + ", maxR -> " + maxR);
                System.out.println("l -> " + l + ", r -> " + r);
                if (maxL <= maxR) {
                    l++;
                    System.out.println("l moved from : " + (l - 1) + " -> " + l);
                    int h = height[l];
                    System.out.println("(l, height[l]) -> " + l + ", " + h);
                    System.out.println("trapped at curr -> " + (maxL - h));
                    System.out.println("water moved from : " + water + " -> " + (water + Math.max(0, maxL - h)));
                    water += Math.max(0, maxL - h);
                    System.out.println("maxL moved from : " + maxL + " -> " + (Math.max(maxL, h)));
                    maxL = Math.max(maxL, h);
                } else {
                    r--;
                    System.out.println("r moved from : " + (r - 1) + " -> " + r);
                    int h = height[r];
                    System.out.println("(r, height[r]) ->" + r + ", " + h);
                    System.out.println("trapped at curr -> " + (maxR - h));
                    System.out.println("water moved from : " + water + " -> " + (water + Math.max(0, maxR - h)));
                    water += Math.max(0, maxR - h);
                    System.out.println("maxR moved from : " + maxR + " -> " + (Math.max(maxR, h)));
                    maxR = Math.max(maxR, h);
                }
            }
            return water;
        }
    }

}
