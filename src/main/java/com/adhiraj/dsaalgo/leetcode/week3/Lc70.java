package com.adhiraj.dsaalgo.leetcode.week3;

import java.util.HashMap;
import java.util.Map;

// This is the memoization way - O(n) time, O(n) space for map and recusrion each
// HAS 1 ALTERNATE SOLUTION - TABULATION
public class Lc70 {
    public int climbStairs(int n) {
        Map<Integer, Integer> mem = new HashMap<>();
        return climbStairs(n, mem);
    }

    private int climbStairs(int n, Map<Integer, Integer> mem) {
        if (n < 2) return 1;
        Integer steps = mem.get(n);

        if (steps != null)
            return steps;

        steps = climbStairs(n - 1, mem) + climbStairs(n - 2, mem); // total no. of ways to climb stairs
        mem.put(n, steps);

        return steps;
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