package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestPermutationBySwaps {
    public static void main(String[] args) {
        List<Integer> digits = new ArrayList<>();
        digits.add(3);
        digits.add(2);
        digits.add(4);
        digits.add(1);
        digits.add(5);
        System.out.print(solve(digits, 3));
    }

    public static List<Integer> solve(List<Integer> digits, int swaps) {
        int n = digits.size();
        int[] digitPositionMap = new int[n + 1];
        for (int i = 0; i < n; i++) {
            digitPositionMap[digits.get(i)] = i;
        }

        for (int i = 0; i < n && swaps > 0; i++) {
            Integer currentDigit = digits.get(i);
            int currentPossibleLargest = n - i;
            if (currentDigit == currentPossibleLargest) continue;
            int largestLoc = digitPositionMap[currentPossibleLargest];
            digits.set(largestLoc, currentDigit);
            digits.set(i, currentPossibleLargest);
            digitPositionMap[currentDigit] = largestLoc;
            digitPositionMap[currentPossibleLargest]  = i;
            swaps--;
        }

        return digits;
    }
}
