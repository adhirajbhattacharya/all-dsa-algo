package com.adhiraj.dsalgo.freecodecamp.dp.tabulation;

public class GridTravellerCount {

    public static void main(String[] args) {
        System.out.println(solve(1, 1));
        System.out.println(solve(1, 2));
        System.out.println(solve(2, 1));
        System.out.println(solve(2, 2));
        System.out.println(solve(3, 2));
        System.out.println(solve(2, 3));
        System.out.println(solve(3, 3));
        System.out.println(solve(10, 10));
        System.out.println(solve(18, 18));
    }

    public static long solve(int row, int col) {
        if (row < 0 || col < 0) throw new IllegalArgumentException("row:" + row + "col:" + col);

//        return solveTab(row, col);
        return solveTabOptimized(row, col);
    }

    private static long solveTabOptimized(int row, int col) {
        if (row == 0 || col == 0) return 0;
        if (row == 1 || col == 1) return 1;

        long[][] memo = new long[2][col + 1];

        for (int i = 1; i < col + 1; i++) {
            memo[0][i] = 1;
        }
        memo[0][0] = 0;
        memo[1][0] = 0;

        for (int i = 2; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                memo[1][j] = memo[0][j] + memo[1][j - 1];
            }
            memo[0] = memo[1];
        }
        return memo[1][col];
    }

    private static long solveTab(int row, int col) {
        if (row == 0 || col == 0) return 0;
        if (row == 1 || col == 1) return 1;

        long[][] memo = new long[row + 1][col + 1];
        for (int i = 1; i < col + 1; i++) {
            memo[0][i] = 0;
            memo[1][i] = 1;
        }
        for (int i = 1; i < row + 1; i++) {
            memo[i][0] = 0;
            memo[i][1] = 1;
        }

        /**
         * this can be improved in space as only previous full row is required at any given time.
         */
        for (int i = 2; i < row + 1; i++) {
            for (int j = 2; j < col + 1; j++) {
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[row][col];
    }
}