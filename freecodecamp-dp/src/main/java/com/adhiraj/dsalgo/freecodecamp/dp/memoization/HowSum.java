package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HowSum {

    public static void main(String[] args) {
        System.out.println(solve(7, new int[]{2, 3}));
        System.out.println(solve(7, new int[]{5, 3, 4, 7}));
        System.out.println(solve(7, new int[]{2, 4}));
        System.out.println(solve(8, new int[]{5, 3, 2}));
        System.out.println(solve(800, new int[]{7, 13}));
        System.out.println(solve(900, new int[]{7, 14}));
    }

    public static List<Integer> solve(int target, int[] nums) {
//        Arrays.sort(nums);
//        return solveMemoMap(target, nums, new HashMap<>());
        return solveMemoArray(target, nums, new List[target + 1], new boolean[target + 1]);
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
