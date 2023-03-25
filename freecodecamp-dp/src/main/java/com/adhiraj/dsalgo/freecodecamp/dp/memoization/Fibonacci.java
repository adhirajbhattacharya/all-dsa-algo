package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.Map;

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
//        return fibMemoMap(n, new HashMap<>());
        return solveMemoArray(n, new Long[n + 1]);
    }

    private static long solveMemoMap(int n, Map<Integer, Long> memo) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        Long val = memo.get(n);
        if (val != null) return val;

        val = solveMemoMap(n - 1, memo) + solveMemoMap(n - 2, memo);
        memo.put(n, val);
        return val;
    }

    private static long solveMemoArray(int n, Long[] memo) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        if (memo[n] != null) return memo[n];

        memo[n] = solveMemoArray(n - 1, memo) + solveMemoArray(n - 2, memo);
        return memo[n];
    }
}
