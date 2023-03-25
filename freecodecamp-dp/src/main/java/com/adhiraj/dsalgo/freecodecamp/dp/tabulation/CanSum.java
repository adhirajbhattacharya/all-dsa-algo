package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

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
        return solveTab(target, nums);
    }

    private static boolean solveTab(int target, int[] nums) {
        boolean[] memo = new boolean[target + 1];
        memo[0] = true;

        for (int i = 1; i < target + 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                int k = i - nums[j];
                if (k < 0) continue;
                if (memo[k]) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[target];
    }
}
