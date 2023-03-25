package com.adhiraj.dsalgo.freecodecamp.dp.recursive;

public class CanSum {

    public static void main(String[] args) {
        System.out.println(solve(7, new int[]{2, 3}));
        System.out.println(solve(7, new int[]{5, 3, 4, 7}));
        System.out.println(solve(7, new int[]{2, 4}));
        System.out.println(solve(8, new int[]{5, 3, 2}));
        System.out.println(solve(900, new int[]{7, 14}));
    }

    public static boolean solve(int target, int[] nums) {
        // check: (target < 0) not required if check: (rem < 0) is done. both are correct to do
        if (target < 0) return false;
        if (target == 0) return true;

        for (int num : nums) {
            int rem = target - num;
            if (rem < 0) continue;

            boolean possible = solve(rem, nums);
            if (possible) return true;
        }
        return false;
    }
}
