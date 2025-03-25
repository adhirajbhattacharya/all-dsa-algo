package com.adhiraj.dsaalgo.leetcode.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. Climbing Stairs
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 * 1 <= n <= 45
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        return climbStairs(n, new HashMap<>());
    }

    private int climbStairs(int n, Map<Integer, Integer> memo) {
        if (n < 2) return 1;
        Integer res = memo.get(n);
        if (res != null) return res;
        res = climbStairs(n - 1, memo) + climbStairs(n - 2, memo);
        memo.put(n, res);
        return res;
    }
}

// Tabulation with O(n) space. Space is improved in 2nd method.
class Lc70AltTabulation {
    public int climbStairs(int n) {
        if (n < 2) return 1;
        int[] arr = new int [n + 1];

        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    public int climbStairsYtVideoWayDpTutorial(int n) {
        if (n < 2) return 1;
        int[] arr = new int [n + 1];

        arr[0] = 1;

        for (int i = 0; i < arr.length; i++) {
            arr[i + 1] += arr[i];
            arr[i + 2] += arr[i];
        }

        return arr[n];
    }

    private int climbStairsBest(int n, boolean improved) {
        if (n < 2) return 1;
        int x = 1, y = 1; // for 0 and 1 stairs

        for (int i = 2; i <= n; i++) {
            int curr = x + y;
            x = y;
            y = curr;
        }

        return y;
    }
}