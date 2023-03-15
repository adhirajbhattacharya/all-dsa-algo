package com.adhiraj.dsaalgo.leetcode.week7;

import com.adhiraj.dsaalgo.leetcode.Triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// This is what i did after someone on leetcode/reddit helped out with alternate with recursion
public class Lc1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        List<Triplet> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            intervals.add(new Triplet(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(intervals, (t1, t2) -> Integer.compare(t1.first, t2.first));

        int[] v = new int[n];

        v[n - 1] = intervals.get(n - 1).third;

        for (int i = n - 2; i >= 0; i--) {
            int j = getNextJob(intervals, i);
            int incl = intervals.get(i).third;
            if (j < n) {
                incl += v[j];
            }
            v[i] = Math.max(v[i + 1], incl);
        }


        return v[0];
    }

    private int getNextJob(List<Triplet> intervals, int idx) {
        int n = intervals.size();
        int lo = idx + 1, hi = n - 1, mid, res = Integer.MAX_VALUE;
        Triplet curr = intervals.get(idx);
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            Triplet next = intervals.get(mid);
            if (next.first >= curr.second) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return res;
    }
}

class Lc1235Alt {


    ///not my solution
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[] memo = new int[startTime.length];
        Arrays.fill(memo, -1);

        List<Triplet> intervals = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            intervals.add(new Triplet(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(intervals, (t1, t2) -> Integer.compare(t1.first, t2.first));
        return jobSchedulinng(memo, intervals, 0);
    }

    private int jobSchedulinng(int[] memo, List<Triplet> intervals, int idx) {
        if (idx >= intervals.size()) return 0;
        if (memo[idx] != -1) return memo[idx];

        int profitIncludingIdx, profitExcludingIdx, maxProfitAtIdx;
        Triplet curr = intervals.get(idx); // current job

        // not including current job
        profitExcludingIdx = jobSchedulinng(memo, intervals, idx + 1);

        // including current job
        int nextIndex = intervals.size();

        // use binary search to find the next valid index if we accept this job and take profit
        int left = 0;
        int right = intervals.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (intervals.get(mid).first >= curr.second) {
                nextIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        profitIncludingIdx = curr.third + jobSchedulinng(memo, intervals, nextIndex);

        maxProfitAtIdx = Math.max(profitIncludingIdx, profitExcludingIdx);
        return (memo[idx] = maxProfitAtIdx); // can you map also -> map.put(idx, max)

    }
}
