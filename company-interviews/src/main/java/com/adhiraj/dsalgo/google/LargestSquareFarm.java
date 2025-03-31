package com.adhiraj.dsalgo.google;

import java.util.Arrays;

public class LargestSquareFarm {
    public static void main(String[] args) {
        /*
         * 0,  1,  1,  0,  1
         * 1,  1,  1,  1,  0
         * 0,  1,  1,  1,  1
         * 1,  1,  1,  1,  1
         * 1,  0,  1,  1,  0
         * 0,  1,  1,  1,  1
         */
        int[][] farm = new int[6][5];
        farm[0] = new int[] {0,  1,  1,  0,  1};
        farm[1] = new int[] {1,  1,  1,  1,  0};
        farm[2] = new int[] {0,  1,  1,  1,  1};
        farm[3] = new int[] {1,  1,  1,  1,  1};
        farm[4] = new int[] {1,  0,  1,  1,  0};
        farm[5] = new int[] {0,  1,  1,  1,  1};
        System.out.println(largestSquareToFarm(farm));

        /*
         * [0][0]
         */
        farm = new int[0][0];
        System.out.println(largestSquareToFarm(farm));

        /*
         * [1][0]
         */
        farm = new int[1][0];
        System.out.println(largestSquareToFarm(farm));

        /*
         * 1
         */
        farm = new int[1][1];
        farm[0] = new int[] {1};
        System.out.println(largestSquareToFarm(farm));

        /*
         * 1,  1
         * 1,  1
         */
        farm = new int[2][2];
        farm[0] = new int[] {1,  1};
        farm[1] = new int[] {1,  1};
        System.out.println(largestSquareToFarm(farm));

        /*
         * 0,  1,  1,  0,  1
         * 1,  0,  1,  1,  0
         * 0,  1,  1,  1,  1
         * 1,  1,  1,  1,  1
         * 1,  0,  1,  1,  0
         * 0,  1,  1,  1,  1
         */
         farm = new int[6][5];
        farm[0] = new int[] {0,  1,  1,  0,  1};
        farm[1] = new int[] {1,  0,  1,  1,  0};
        farm[2] = new int[] {0,  1,  1,  1,  1};
        farm[3] = new int[] {1,  1,  1,  1,  1};
        farm[4] = new int[] {1,  0,  1,  1,  0};
        farm[5] = new int[] {0,  1,  1,  1,  1};
        System.out.println(largestSquareToFarm2(farm));
    }

    private static int largestSquareToFarm(int[][] farm) {
        int rmax = farm.length;
        if (rmax == 0) return 0;
        int cmax = farm[0].length;
        if (cmax == 0) return 0;
        int[][] dp = new int[rmax][cmax];
        for (int i = 0; i < rmax; i++) {
            Arrays.fill(dp[i], -1);
        }
        fillLargestSquareDp(farm, 0, 0, rmax - 1, cmax - 1, dp);
        int res = dp[0][0];
        for (int i = 0; i < rmax; i++) {
            for (int j = 0; j < cmax; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }

    private static int fillLargestSquareDp(int[][] farm, int r, int c, int rmax, int cmax, int[][] dp) {
        if (r > rmax || c > cmax) return 0;

        if (dp[r][c] != -1) return dp[r][c];

        int right = fillLargestSquareDp(farm, r, c + 1, rmax, cmax, dp);
        int down = fillLargestSquareDp(farm, r + 1, c, rmax, cmax, dp);
        int diag = fillLargestSquareDp(farm, r + 1, c + 1, rmax, cmax, dp);

        if (farm[r][c] == 0) dp[r][c] = 0;
        else dp[r][c] = 1 + Math.min(Math.min(right, down), diag);

        return dp[r][c];
    }


    private static int largestSquareToFarm2(int[][] farm) {
        int rmax = farm.length;
        if (rmax == 0) return 0;
        int cmax = farm[0].length;
        if (cmax == 0) return 0;
        return largestSquareToFarm2(farm, 0, 0, rmax - 1, cmax - 1);
    }

    private static int largestSquareToFarm2(int[][] farm, int r, int c, int rmax, int cmax) {
        if (r > rmax || c > cmax) return 0;

        int right = largestSquareToFarm2(farm, r, c + 1, rmax, cmax);
        int down = largestSquareToFarm2(farm, r + 1, c, rmax, cmax);
        int diag = largestSquareToFarm2(farm, r + 1, c + 1, rmax, cmax);

        int largest = farm[r][c];

        largest = Math.max(largest, right);
        largest = Math.max(largest, down);
        largest = Math.max(largest, diag);

        if (farm[r][c] == 1) {
            largest = 1 + Math.min(Math.min(right, down), diag);
        }

        return largest;
    }

}
