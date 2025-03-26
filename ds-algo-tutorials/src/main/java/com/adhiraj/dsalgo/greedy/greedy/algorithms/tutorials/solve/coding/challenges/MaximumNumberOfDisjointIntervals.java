package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MaximumNumberOfDisjointIntervals {
    public static void main(String[] args) {

    }

    /**
     * Input = [1,2], [2,10], [4,6]
     * Output = 2
     * How -> [1,2], [4,6]
     */
    public static int solve(List<List<Integer>> intervals) {
        if (Objects.isNull(intervals)) return 0;
        if (intervals.size() < 2) return intervals.size();

        Collections.sort(intervals, Comparator.comparing(a -> a.get(1)));

        int prev_st = intervals.get(0).get(0);
        int prev_en = intervals.get(0).get(1);
        int count = 1;

        for (List<Integer> set : intervals) {
            // current start is lower than prev end means overlap
            if (set.get(0) <= prev_en) continue;

            prev_st = set.get(0);
            prev_en = set.get(1);
            count++;
        }
        return count;
    }
}
