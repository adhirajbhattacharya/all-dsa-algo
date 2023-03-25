package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HowSum {

    public static void main(String[] args) {
        System.out.println(solve(0, new int[]{2, 3}));
        System.out.println(solve(7, new int[]{2, 3}));
        System.out.println(solve(7, new int[]{5, 3, 4, 7}));
        System.out.println(solve(7, new int[]{2, 4}));
        System.out.println(solve(8, new int[]{5, 3, 2}));
        System.out.println(solve(800, new int[]{7, 13}));
        System.out.println(solve(900, new int[]{7, 14}));
    }

    public static List<Integer> solve(int target, int[] nums) {
        return solveTab(target, nums);
    }

    private static List<Integer> solveTab(int target, int[] nums) {
        List<Integer>[] memo = new List[target + 1];
        memo[0] = new ArrayList<>();

        for (int i = 1; i < target + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                int k = i - nums[j];
                if (k < 0) continue;
                if (memo[k] != null && memo[i] == null) {
                    memo[i] = new ArrayList<>(memo[k]);
                    memo[i].add(nums[j]);
                }
            }
        }

        return memo[target];
    }

    private static List<Integer> solveMemoMap(int target, int[] nums, HashMap<Integer, List<Integer>> memo) {
        if (target < 0) return null;
        if (target == 0) return new ArrayList<>();

        if (memo.containsKey(target)) return memo.get(target);

        for (int num : nums) {
            int rem = target - num;

            List<Integer> val = solveMemoMap(rem, nums, memo);
            if (val != null) {
                val = new ArrayList<>(val);
                val.add(num);
                memo.put(target, val);
                return val;
            }
        }
        memo.put(target, null);
        return null;
    }

    private static List<Integer> solveMemoArray(int target, int[] nums, List<Integer>[] memo, boolean[] done) {
        if (target < 0) return null;
        if (target == 0) return new ArrayList<>();

        if (done[target]) return memo[target];

        for (int num : nums) {
            int rem = target - num;

            List<Integer> val = solveMemoArray(rem, nums, memo, done);
            if (val != null) {
                val = new ArrayList<>(val);
                val.add(num);
                done[target] = true;
                memo[target] = val;
                return val;
            }
        }
        done[target] = true;
        memo[target] = null;
        return null;
    }
}
