package com.adhiraj.dsaalgo.leetcode;

import java.util.Arrays;

public class test2 {
    public static void main(String[] args) {
        System.out.println(new test2().largestRectangleArea(new int[]{2,1, 5, 6, 2, 3}));
    }

    public int largestRectangleArea(int[] heights) {
        // create segment tree
        int n = heights.length;
        int h = (int) Math.ceil((Math.log(n) / Math.log(2)));
        int[] st = new int[((int) Math.pow(2, h + 1)) - 1];
        Arrays.fill(st, -1);
        st = createRangeMinSt(heights, 0, n - 1, st, 0);

        return largestRectangleArea(heights, 0, n - 1, st);

    }

    private int largestRectangleArea(int[] heights, int start, int end, int[] st) {
        if (start < 0 || end >= heights.length) return Integer.MIN_VALUE;

        int min = getMinH(st, start, end, 0, heights.length - 1, 0, heights);

        int incl = min == -1 ? 0 : heights[min] * (end - start + 1);
        int left = largestRectangleArea(heights, start, min - 1, st);
        int right = largestRectangleArea(heights, min + 1, end, st);

        return Math.max(Math.max(incl, left), right);

    }

    private int getMinH(int[] st, int lo, int hi, int start, int end, int idx, int[] heights) {
        if (lo > end || hi < start) {
            return -1;
        }

        if (lo <= start && hi >= end) {
            return st[idx];
        }

        int mid = (start + end) / 2;

        int leftidx = getMinH(st, lo, hi, start, mid, 2 * idx + 1, heights);
        int rightidx = getMinH(st, lo, hi, mid + 1, end, 2 * idx + 2, heights);

        if (leftidx == -1) return rightidx;
        if (rightidx == -1) return leftidx;

        if (heights[leftidx] < heights[rightidx])
            return leftidx;
        return rightidx;
    }

    private int[] createRangeMinSt(int[] heights, int start, int end, int[] st, int idx) {
        if (start > end) return st;
        if (start == end) {
            st[idx] = start;
            return st;
        }

        int mid = (start + end) / 2;

        createRangeMinSt(heights, start, mid, st, 2 * idx + 1);
        createRangeMinSt(heights, mid + 1, end, st, 2 * idx + 2);

        if (heights[st[2 * idx + 1]] < heights[st[2 * idx + 2]]) {
            st[idx] = st[2 * idx + 1];
        } else {
            st[idx] = st[2 * idx + 2];
        }

        return st;
    }
}