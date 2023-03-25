package com.adhiraj.dsalgo.freecodecamp.dp.recursive;

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
        if (row == 0 || col == 0) return 0;
        /**
         * base case.
         * can either be this or grid of (1, 1).
         * this will cause the base to appear quickly.
         */
        if (row == 1 || col == 1) return 1;

        return solve(row - 1, col) + solve(row, col - 1);
    }
}
