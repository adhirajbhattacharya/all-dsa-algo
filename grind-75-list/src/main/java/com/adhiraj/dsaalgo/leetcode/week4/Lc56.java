package com.adhiraj.dsaalgo.leetcode.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc56 {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        List<int[]> res = new ArrayList<>();

        int[] prev = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (prev[1] < intervals[i][0]) {
                res.add(prev);
                prev = intervals[i];
                continue;
            }
            prev[0] = Math.min(prev[0], intervals[i][0]);
            prev[1] = Math.max(prev[1], intervals[i][1]);
        }

        res.add(prev);
        return res.toArray(new int[0][0]);
    }
}