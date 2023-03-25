package com.adhiraj.dsaalgo.leetcode.week8;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.HashMap;

public class Lc62 {
    public int uniquePaths(int m, int n) {
        return uniquePaths(0, 0, m, n, new HashMap<>());
    }

    private int uniquePaths(int i, int j, int m, int n, HashMap<Pair, Integer> memo) {
        if (i == m || j == n) {
            return 0;
        }

        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        Pair key = new Pair(i, j);

        Integer ans = memo.get(key);
        if (ans != null) return ans;

        ans = uniquePaths(i, j + 1, m, n, memo) + uniquePaths(i + 1, j, m, n, memo); // right + down
        memo.put(key, ans);

        return ans;
    }
}
