package com.adhiraj.dsalgo.backtracking.solve.coding.interview.backtracking.problems.crash.course;

import java.util.*;

public class PermutationWithDuplicates {

    public static void main(String[] args) {
        Object a = new PermutationWithDuplicates().permuteUnique(new int[]{2, 2, 1, 1});
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> solution = new HashSet<>();
        permute(nums, new LinkedHashSet<>(), solution);
        return new ArrayList<>(solution);
    }

    private Set<List<Integer>> permute(int[] nums, Set<Integer> currPerm, Set<List<Integer>> solution) {
        if (nums.length == currPerm.size()) {
            solution.add(getPerm(nums, currPerm));
            return solution;
        }

        for (int i = 0; i < nums.length; i++) {
            if (currPerm.contains(i)) continue;
            currPerm.add(i);
            permute(nums, currPerm, solution);
            currPerm.remove(i);
        }

        return solution;
    }

    private List<Integer> getPerm(int[] nums, Set<Integer> perm) {
        List<Integer> numList = new ArrayList<>();
        for (int idx : perm) {
            numList.add(nums[idx]);
        }
        return numList;
    }
}
