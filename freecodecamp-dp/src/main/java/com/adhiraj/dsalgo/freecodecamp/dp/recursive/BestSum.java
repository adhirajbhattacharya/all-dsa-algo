package com.adhiraj.dsalgo.freecodecamp.dp.recursive;

import lombok.val;

import java.util.ArrayList;
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
        if (target < 0) return null;
        if (target == 0) return new ArrayList<>();

        List<Integer> val = null;

        for (int num : nums) {
            int rem = target - num;

            List<Integer> curr = solve(rem, nums);
            if (curr != null && (val == null || val.size() - 1 > curr.size()))  {
                curr.add(num);
                val = curr;
            }
        }
        return val;
    }
}
