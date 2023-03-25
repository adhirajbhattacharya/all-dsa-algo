package com.adhiraj.dsaalgo.leetcode.week1;

public class Lc121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        int buy = prices[0], maxProfit = 0;

        for (int i = 1; i < n; i++) {
            int curr = prices[i];
            if (curr <= buy)
                buy = curr;
            else
                maxProfit = Math.max(maxProfit, curr - buy);
        }
        return maxProfit;
    }
}
