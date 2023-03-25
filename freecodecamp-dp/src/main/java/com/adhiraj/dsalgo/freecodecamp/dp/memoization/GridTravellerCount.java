package com.adhiraj.dsalgo.freecodecamp.dp.memoization;

import java.util.HashMap;

public class GridTravellerCount {

    public static void main(String[] args) {
        System.out.println(solve(1,1));
        System.out.println(solve(1,2));
        System.out.println(solve(2,1));
        System.out.println(solve(2,2));
        System.out.println(solve(3,2));
        System.out.println(solve(2,3));
        System.out.println(solve(3,3));
        System.out.println(solve(10,10));
        System.out.println(solve(18,18));
    }

    public static long solve(int row, int col) {
        if (row < 0 || col < 0) throw new IllegalArgumentException("row:" + row + "col:" + col);
        return solveMemoMap(row, col, new HashMap<>());
//        return solveMemoArray(row, col, new Long[row + 1][col + 1]);
    }

    private static long solveMemoMap(int row, int col, HashMap<Pair, Long> memo) {
        if (row == 0 || col == 0) return 0;
        /**
         * base case.
         * can either be this or grid of (1, 1).
         * this will cause the base to appear quickly.
         */
        if (row == 1 || col == 1) return 1;

        Pair key = new Pair(row, col);
        Pair altKey = new Pair(col, row);
        Long val = memo.get(key);
        if (val != null) return val;
        val = memo.get(altKey);
        if (val != null) return val;

        val = solveMemoMap(row - 1, col, memo) + solveMemoMap(row, col - 1, memo);
        memo.put(key, val);
        return val;
    }

    private static long solveMemoArray(int row, int col, Long[][] memo) {
        if (row == 0 || col == 0) return 0;
        /**
         * base case.
         * can either be this or grid of (1, 1).
         * this will cause the base to appear quickly.
         */
        if (row == 1 || col == 1) return 1;

        if (memo[row][col] != null) return memo[row][col];

        memo[row][col] = solveMemoArray(row - 1, col, memo) + solveMemoArray(row, col - 1, memo);
        return memo[row][col];
    }
}