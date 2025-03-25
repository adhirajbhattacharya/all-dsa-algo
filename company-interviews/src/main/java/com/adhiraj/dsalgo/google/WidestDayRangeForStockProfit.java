package com.adhiraj.dsalgo.google;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WidestDayRangeForStockProfit {
    public static void main(String[] args) {
        int[] prices = Arrays.stream("53 52 51 54 49 60 58 57 55 63 61 59 58 72 70 69".split(" ")).map(Integer::parseInt).mapToInt(x -> x).toArray();
        System.out.println(new WidestDayRangeForStockProfit().solution(prices));
    }

    /**
     * Given the stock prices for a continuos range of days return the widest<br>
     * range of days over which the stock has a profit.<br><br>
     * Example 1:<br>
     * [00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15]<br>
     * [53 52 51 54 49 60 58 57 55 63 61 59 58 72 70 69]<br>
     * Longest Ranges: 53->69 (15)<br>
     * Example 2:<br>
     * [00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16]<br>
     * [65 60 52 51 54 61 58 57 55 53 50 59 58 59 57 56 69]<br>
     * Longest Ranges: 53->69 (15)<br>
     */

    public int solution(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(0);
        int maxRange = 0;

        for (int i = 1; i < prices.length; i++) {
            int prev = prices[stack.peekLast()];
            int curr = prices[i];

            if (prev >= curr) {
                stack.offerLast(i);
                continue;
            }
            int prevIdx = i;
            while (!stack.isEmpty() && prices[stack.peekLast()] < curr) {
                prevIdx = stack.pollLast();
            }
            if (stack.isEmpty()) prevIdx = 0;
            maxRange = Math.max(maxRange, i - prevIdx);
            stack.offerLast(i);
        }
        return maxRange;
    }
}
