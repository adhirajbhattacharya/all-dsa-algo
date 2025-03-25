package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.HashMap;
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
//        return solveMemoMap(n, new HashMap<>());
        return solveMemoArray(n, new Long[n + 1]);
    }

    private static long solveMemoMap(int n, Map<Integer, Long> memo) {
        if (n < 2) return n;
        Long result = memo.get(n);
        if (result != null) return result;
        result = solveMemoMap(n - 1, memo) + solveMemoMap(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    private static long solveMemoArray(int n, Long[] memo) {
        if (n < 2) return n;
        Long result = memo[n];
        if (result != null) return result;
        result = solveMemoArray(n - 1, memo) + solveMemoArray(n - 2, memo);
        memo[n] = result;
        return result;
    }
}
