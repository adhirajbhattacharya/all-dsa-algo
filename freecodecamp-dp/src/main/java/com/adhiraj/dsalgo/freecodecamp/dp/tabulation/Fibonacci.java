package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(solve(0));
        System.out.println(solve(1));
        System.out.println(solve(2));
        System.out.println(solve(3));
        System.out.println(solve(4));
        System.out.println(solve(5));
        System.out.println(solve(6));
        System.out.println(solve(7));
        System.out.println(solve(8));
        System.out.println(solve(9));
        System.out.println(solve(50));
        System.out.println(solve(1000));
    }

    public static long solve(int n) {
        if (n < 0) throw new IllegalArgumentException("n:" + n);
//        return solveTab(n);
        return solveTabOptimized(n);
    }

    private static long solveTabOptimized(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        long p1 = 1, p2 = 2, curr = 0;

        for (int i = 3; i < n + 1; i++) {
            curr = p1 + p2;
            p1 = p2;
            p2 = curr;
        }

        return curr;
    }

    public static long solveTab(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;

        long[] memo = new long[n + 1];
        memo[1] = 1;
        memo[2] = 1;

        /**
         * this can be optimized as only prev 2 elements are required.
         */
        for (int i = 3; i < memo.length; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }
}
