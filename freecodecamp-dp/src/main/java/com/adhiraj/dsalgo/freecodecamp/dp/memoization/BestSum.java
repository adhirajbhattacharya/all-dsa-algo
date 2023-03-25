package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BestSum {

    public static void main(String[] args) {
        System.out.println(solve(7, new int[]{2, 3}));
        System.out.println(solve(7, new int[]{5, 3, 4, 7}));
        System.out.println(solve(7, new int[]{2, 4}));
        System.out.println(solve(8, new int[]{5, 3, 2}));
        System.out.println(solve(800, new int[]{7, 13}));
        System.out.println(solve(800, new int[]{7, 13, 20}));
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

        List<Integer> val = null;
        if (memo.containsKey(target)) {
            val = memo.get(target);
            return val == null ? null : new ArrayList<>(val);
        }

        for (int num : nums) {
            int rem = target - num;

            List<Integer> curr = solveMemoMap(rem, nums, memo);
            if (curr != null && (val == null || val.size() - 1 > curr.size())) {
                curr.add(num);
                val = curr;
            }
        }
        memo.put(target, val);
        return val == null ? null : new ArrayList<>(val);
    }

    private static List<Integer> solveMemoArray(int target, int[] nums, List[] memo, boolean[] done) {
        if (target < 0) return null;
        if (target == 0) return new ArrayList<>();

        List<Integer> val = null;
        if (done[target]) {
            val = memo[target];
            return val == null ? null : new ArrayList<>(val);
        }

        for (int num : nums) {
            int rem = target - num;

            List<Integer> curr = solveMemoArray(rem, nums, memo, done);
            if (curr != null && (val == null || val.size() - 1 > curr.size())) {
                curr.add(num);
                val = curr;
            }
        }
        done[target] = true;
        memo[target] = val;
        return val == null ? null : new ArrayList<>(val);
    }
}
