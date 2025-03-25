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
//        return solveMemoMap(target, nums, new HashMap<>());
        return solveMemoArray(target, nums, new List[target + 1], new boolean[target + 1]);
    }

    private static List<Integer> solveMemoMap(int target, int[] nums, HashMap<Integer, List<Integer>> memo) {
        if (target == 0) return new ArrayList<>();
        if (memo.containsKey(target)) return memo.get(target);
        List<Integer> result = null;
        for (int num : nums) {
            int remainder = target - num;
            if (remainder < 0) continue;
            List<Integer> currResult = solveMemoMap(remainder, nums, memo);
            if (currResult == null) continue;
            if (result == null) {
                result = new ArrayList<>(currResult);
                result.add(num);
            } else if (currResult.size() + 1 < result.size()) {
                result = new ArrayList<>(currResult);
                result.add(num);
            }
        }
        memo.put(target, result);
        return result;
    }

    private static List<Integer> solveMemoArray(int target, int[] nums, List<Integer>[] memo, boolean[] done) {
        if (target == 0) return new ArrayList<>();
        if (done[target]) {
            return memo[target];
        }
        List<Integer> result = null;
        for (int num : nums) {
            int remainder = target - num;
            if (remainder < 0) continue;
            List<Integer> currResult = solveMemoArray(remainder, nums, memo, done);
            if (currResult == null) continue;
            if (result == null) {
                result = new ArrayList<>(currResult);
                result.add(num);
            } else if (currResult.size() + 1 < result.size()) {
                result = new ArrayList<>(currResult);
                result.add(num);
            }
        }
        done[target] = true;
        memo[target] = result;
        return result;
    }
}
