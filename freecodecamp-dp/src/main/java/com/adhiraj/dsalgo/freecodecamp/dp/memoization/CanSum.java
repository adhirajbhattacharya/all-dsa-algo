package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.HashMap;

public class CanSum {

    public static void main(String[] args) {
        System.out.println(solve(7, new int[]{2, 3}));
        System.out.println(solve(7, new int[]{5, 3, 4, 7}));
        System.out.println(solve(7, new int[]{2, 4}));
        System.out.println(solve(8, new int[]{5, 3, 2}));
        System.out.println(solve(800, new int[]{7, 13}));
        System.out.println(solve(900, new int[]{7, 14}));
    }

    public static boolean solve(int target, int[] nums) {
        return solveMemoMap(target, nums, new HashMap<>());
//        return solveMemoArray(target, nums, new Boolean[target + 1]);
    }

    private static boolean solveMemoMap(int target, int[] nums, HashMap<Integer, Boolean> memo) {
        // check: (target < 0) not required if check: (rem < 0) is done. both are correct to do
        if (target < 0) return false;
        if (target == 0) return true;

        Boolean val = memo.get(target);
        if (val != null) return val;

        for (int num : nums) {
            int rem = target - num;
            if (rem < 0) continue;

            boolean possible = solveMemoMap(rem, nums, memo);
            if (possible) {
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
    }

    private static boolean solveMemoArray(int target, int[] nums, Boolean[] memo) {
        // check: (target < 0) not required if check: (rem < 0) is done. both are correct to do
        if (target < 0) return false;
        if (target == 0) return true;

        if (memo[target] != null) return memo[target];

        for (int num : nums) {
            int rem = target - num;
            if (rem < 0) continue;

            boolean possible = solveMemoArray(rem, nums, memo);
            if (possible) {
                memo[target] = true;
                return true;
            }
        }
        memo[target] = false;
        return false;
    }
}
