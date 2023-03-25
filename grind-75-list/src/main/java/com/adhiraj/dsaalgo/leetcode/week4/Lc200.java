package com.adhiraj.dsaalgo.leetcode.week4;

import java.util.ArrayDeque;
import java.util.Queue;

// This is the dfs solution.
// Check bfs below. Intially was giving TLE. Check the comment which fixed it.
public class Lc200 {
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] graph, int row, int col) {
        int[] dir = {-1, 0, 1, 0, -1};

        graph[row][col] = '2';

        for (int i = 0; i < dir.length - 1; i++) {
            int r = row + dir[i], c = col + dir[i + 1];
            if (isValidIndex(graph, r, c) && graph[r][c] == '1')
                dfs(graph, r, c);
        }
    }

    private boolean isValidIndex(char[][] graph, int row, int col) {
        return row > -1 && col > -1 && row < graph.length && col < graph[0].length;
    }
}

class Lc200AltBfs {
    public int numIslands(char[][] grid) {
        int count = 0;

        Queue<int[]> q = new ArrayDeque<>();
        int[] dir = {-1, 0, 1, 0, -1};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    q.offer(new int[] {i, j});
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();

                        for (int k = 0; k < dir.length - 1; k++) {
                            int r = curr[0] + dir[k], c = curr[1] + dir[k + 1];
                            if (isValidIndex(grid, r, c) && grid[r][c] == '1') {
                                grid[r][c] = '2';   // always mark visit in BFS before offering to queue
                                q.offer(new int[]{r, c});
                            }
                        }
                    }
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isValidIndex(char[][] graph, int row, int col) {
        return row > -1 && col > -1 && row < graph.length && col < graph[0].length;
    }
}