package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.VersionControl;

public class Lc278 extends VersionControl {
    public int firstBadVersion(int n) {

        int lo = 1, hi = n, mid, bad = n;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            boolean isBad = isBadVersion(mid);
            if (isBad) {
                bad = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return bad;
    }
}

