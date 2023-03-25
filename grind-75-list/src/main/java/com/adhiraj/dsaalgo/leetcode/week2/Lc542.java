package com.adhiraj.dsaalgo.leetcode.week2;

import java.util.ArrayDeque;
import java.util.Queue;

// can also be done by bfs, check below alternate solution
public class Lc542 {
    public int[][] updateMatrix(int[][] mat) {
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
}

// by bfs
class Lc542Alt {
    public int[][] updateMatrix(int[][] mat) {
        return bfs(mat);
    }

    public int[][] bfs(int[][] mat) {
        int[] DIR = new int[]{0, 1, 0, -1, 0};
        int m = mat.length, n = mat[0].length; // The distance of cells is up to (M+N)
        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < m; ++r)
            for (int c = 0; c < n; ++c)
                if (mat[r][c] == 0) q.offer(new int[]{r, c});
                else mat[r][c] = -1; // Marked as not processed yet!

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            for (int i = 0; i < 4; ++i) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr < 0 || nr == m || nc < 0 || nc == n || mat[nr][nc] != -1) continue;
                mat[nr][nc] = mat[r][c] + 1;
                q.offer(new int[]{nr, nc});
            }
        }
        return mat;
    }
}
