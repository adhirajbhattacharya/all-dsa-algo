package com.adhiraj.dsaalgo.leetcode.week6;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.HashMap;
import java.util.Map;

// You can only select element at current index once. So choice choose or not and move to next.
// Had done a DP Top Down tabulation but dont seem to connect on it now. Find below.
public class Lc416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 == 1) return false;

        int target = sum / 2;
        return canPartition(nums, target, 0, new HashMap<>());
    }

    private boolean canPartition(int[] nums, int target, int idx, Map<Pair, Boolean> memo) {
        if (target == 0) return true;
        if (target < 0) return false;
        if (idx == nums.length) return false;

        Pair key = new Pair(target, idx);
        Boolean res = memo.get(key);
        if (res != null) return res;

        res = canPartition(nums, target - nums[idx], idx + 1, memo) || canPartition(nums, target, idx + 1, memo);
        memo.put(key, res);
        return res;
    }
}

class Lc416Alt {
    int[][] dp;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 == 1)
            return false;
        dp = new int[sum / 2 + 1][nums.length];
        int resultSum = sum / 2;
        boolean ans = findSubset(nums,0,resultSum);
        return ans;
    }
    public boolean findSubset(int[] nums, int index, int sum) {
        if (index == nums.length || sum <= 0)
            return sum == 0 ? true : false;
        if (dp[sum][index] !=0)
            return dp[sum][index] == 1 ? true : false;
        boolean val = findSubset(nums, index + 1, sum-nums[index]) || findSubset(nums, index + 1, sum);
        dp[sum][index] = val == true ? 1 : 2;
        return val;
    }
}