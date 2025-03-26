package com.adhiraj.dsalgo.greedy.greedy.algorithms.tutorials.solve.coding.challenges;

import java.util.Arrays;
import java.util.List;

public class GasStation {
    public static void main(String[] args) {
        System.out.println(solve(Arrays.asList(3,5,2,1,7), Arrays.asList(4,2,1,9,1)));
    }

    public static int solve(List<Integer> gas, List<Integer> cost) {
        int n = gas.size();
        int i = 0;
        while (i < n) {
            int curr = 0;
            for (int j = i; j < 2 * n; j++) {
                if (j != i && j % n == i) return j % n;
                curr += (gas.get(j % n) - cost.get(j % n));
                if (curr < 0) {
                    i = j + 1;
                    break;
                }
            }
        }

        return -1;
    }
}
