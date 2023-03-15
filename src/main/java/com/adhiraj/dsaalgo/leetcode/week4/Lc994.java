package com.adhiraj.dsaalgo.leetcode.week4;

import java.util.ArrayDeque;
import java.util.Queue;

// BFS. DFS will not do.
// Visit marking in BFS should be done at offering stage only. Note comment below.
public class Lc994 {
    public int orangesRotting(int[][] grid) {

        int minutes = 0, fresh = 0;
        int[] dir = {-1, 0, 1, 0, -1};
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        q.offer(new int[] {-1, -1});

        // if visit marking and fresh orange decrease is done here, then answer will be incorrect.
        // this may cause duplicate adding of same fresh orange at different points in time, causing illusion of more minutes.

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == -1 && q.isEmpty()) continue;
            if (curr[0] == -1) {
                minutes++;
                q.offer(curr);
                curr = q.poll();
            }

            for (int i = 0; i < dir.length - 1; i++) {
                int r = curr[0] + dir[i], c = curr[1] + dir[i + 1];
                if (isValidIndex(grid, r, c) && grid[r][c] == 1) {
                    fresh--;
                    grid[r][c] = 2; // visit must be maerked before offering, so any visiter coming here before processing may know.
                    q.offer(new int[] {r, c});
                }
            }

        }

        return fresh == 0 ? minutes : -1;
    }

    private boolean isValidIndex(int[][] graph, int row, int col) {
        return row > -1 && col > -1 && row < graph.length && col < graph[0].length;
    }
}