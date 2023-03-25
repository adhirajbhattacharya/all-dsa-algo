package com.adhiraj.dsaalgo.leetcode.week1;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

// bfs solution, maybe dfs faster?? -- check dfs in alt solution
public class Lc733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        if (image[sr][sc] == color) return image;

        int m = image.length;
        int n = image[0].length;

        Queue<Pair> bfsq = new ArrayDeque<>();
        bfsq.offer(new Pair(sr, sc));

        int oldColor = image[sr][sc];

        int[] dir = new int[] {-1, 0, 1, 0, -1};

        while (!bfsq.isEmpty()) {
            Pair curr = bfsq.poll();
            for (int i = 0; i < 4; i++) {
                int r = curr.first + dir[i];
                int c = curr.second + dir[i + 1];
                if (r > -1 && r < m && c > -1 && c < n && image[r][c] == oldColor)
                    bfsq.offer(new Pair(r, c));
            }
            image[curr.first][curr.second] = color;
        }

        return image;
    }
}

class Lc733Alt {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[] dir = new int[] {-1, 0, 1, 0, -1};

        dfs(image, sr, sc, image[sr][sc], color, dir);

        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor, int[] dir) {
        if (image[r][c] != oldColor || image[r][c] == newColor) return;

        image[r][c] = newColor;

        for (int i = 0; i < dir.length - 1; i++) {
            if (!isValidCoordinate(r + dir[i], c + dir [i + 1], image.length, image[0].length)) continue;
            dfs(image, r + dir[i], c + dir[i + 1], oldColor, newColor, dir);
        }
    }

    private boolean isValidCoordinate(int r, int c, int rowMax, int colMax) {
        return r > -1 && c > -1 && r < rowMax && c < colMax;
    }
}
