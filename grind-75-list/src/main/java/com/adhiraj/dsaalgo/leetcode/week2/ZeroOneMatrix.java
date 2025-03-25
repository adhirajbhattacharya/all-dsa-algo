package com.adhiraj.dsaalgo.leetcode.week2;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 542. 01 Matrix
 *
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */

/**
 * O(M*N) TIME & O(1) SPACE
 */
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        Queue<Pair> bfsq = new ArrayDeque<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) bfsq.offer(new Pair(i, j));
                else mat[i][j] = Integer.MIN_VALUE;
            }
        }

        int[] dir = new int[] {-1, 0, 1, 0, -1};

        while (!bfsq.isEmpty()) {
            Pair curr = bfsq.poll();
            for (int i = 0; i < dir.length - 1; i++) {
                int r = curr.first + dir[i];
                int c = curr.second + dir[i + 1];
                if (!isValidCoordinate(r, c, mat.length, mat[0].length)) continue;
                if (mat[r][c] != Integer.MIN_VALUE) continue;
                mat[r][c] = 1 + mat[curr.first][curr.second];
                bfsq.offer(new Pair(r, c));
            }
        }
        return mat;
    }

    private boolean isValidCoordinate(int row, int col, int maxrow, int maxcol) {
        return row > -1 && col > -1 && row < maxrow && col < maxcol;
    }

    /**
     * Alternate and simple but 2 pass algo. One from left to right and then reverse. Not very inspiring.
     */
    public int[][] updateMatrixAlternate(int[][] mat) {
        int row = mat.length, col = mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mat[i][j] = checkNorthWest(mat, i, j);
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                mat[i][j] = checkSouthEast(mat, i, j, row, col);
            }
        }

        return mat;
    }

    private int checkNorthWest(int[][] mat, int i, int j) {
        if (mat[i][j] == 0)
            return 0;

        int north = Integer.MAX_VALUE, west = Integer.MAX_VALUE;

        if (i > 0 && mat[i - 1][j] != Integer.MAX_VALUE) {
            north = mat[i - 1][j] + 1;
        }
        if (j > 0 && mat[i][j - 1] != Integer.MAX_VALUE) {
            west = mat[i][j - 1] + 1;
        }

        return Math.min(north, west);
    }

    private int checkSouthEast(int[][] mat, int i, int j, int row, int col) {
        if (mat[i][j] == 0)
            return 0;

        int south = mat[i][j], east = mat[i][j];

        if (i < row - 1) {
            south = Math.min(mat[i + 1][j] == Integer.MAX_VALUE ? mat[i + 1][j] : mat[i + 1][j] + 1, south);
        }
        if (j < col - 1) {
            east = Math.min(mat[i][j + 1] == Integer.MAX_VALUE ? mat[i][j + 1] : mat[i][j + 1] + 1, east);
        }

        return Math.min(south, east);
    }

    public static void main(String[] args) {
        System.out.println(new ZeroOneMatrix().updateMatrix(new int[][] {{0,0,0}, {0,1,0}, {1,1,1}}));
    }
}
