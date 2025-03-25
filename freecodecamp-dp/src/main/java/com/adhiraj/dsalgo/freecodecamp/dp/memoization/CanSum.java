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
//        return solveMemoMap(target, nums, new HashMap<>());
        return solveMemoArray(target, nums, new Boolean[target + 1]);
    }

    private static boolean solveMemoMap(int target, int[] nums, HashMap<Integer, Boolean> memo) {
        if (target == 0) return true;
        Boolean result = memo.get(target);
        if (result != null) return result;
        result = false;
        for (int num : nums) {
            int remainder = target - num;
            if (remainder < 0) continue;
            result = Boolean.logicalOr(result, solveMemoMap(remainder, nums, memo));
            if (result) break;
        }
        memo.put(target, result);
        return result;
    }

    private static boolean solveMemoArray(int target, int[] nums, Boolean[] memo) {
        if (target == 0) return true;
        Boolean result = memo[target];
        if (result != null) return result;
        result = false;
        for (int num : nums) {
            int rem = target - num;
            if (rem < 0) continue;
            result = Boolean.logicalOr(result, solveMemoArray(rem, nums, memo));
            if (result) break;
        }
        memo[target] = result;
        return result;
    }
}
