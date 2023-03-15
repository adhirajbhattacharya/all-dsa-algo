package com.adhiraj.dsaalgo.leetcode.week1;

import java.util.ArrayList;
import java.util.List;

// this is the first solution that came to mind, logically correct but very complex.
// Check alternate solution done before by me but forgotten later, much cleaner logic
public class Lc57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        int n = intervals.length;
        if (n == 0) {
            result.add(newInterval);
            return result.toArray(new int[0][0]);
        }

        int i = 0;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        boolean overlap = false;
        if (i == n) {
            result.add(newInterval);
        } else if (intervals[i][0] > newInterval[1]) {
            result.add(newInterval);
        } else {
            overlap = true;
            while (i < n && overlap) {
                newInterval = new int[] {Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])};
                i++;
                if (i < n && intervals[i][0] > newInterval[1]) {
                    overlap = false;
                    result.add(newInterval);
                    break;
                }
            }
        }

        if (overlap == true)
            result.add(newInterval);
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[0][0]);

    }
}

class Lc57Alt {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0])
                // current ends before start of new
                result.add(intervals[i]);
            else if (intervals[i][0] > newInterval[1]) {
                // current starts after end of new
                result.add(newInterval);
                newInterval = intervals[i];
            } else {
                // current overlaps with new
                newInterval = new int[] {Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])};
                /* better for memory
                 * newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                 * newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
                 */
            }
        }
        // this will always have one remaining, in newInterval
        result.add(newInterval);

        return result.toArray(new int[0][0]);
    }
}