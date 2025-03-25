package com.adhiraj.dsaalgo.leetcode.week1;

import com.adhiraj.dsaalgo.leetcode.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 733. Flood Fill
 *
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 *
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 *
 * Constraints:
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */

/**
 * O(M*N) SPACE & 0(1) SPACE
 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        if (image[sr][sc] == color) return image;

        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];

        Queue<Pair> bfsq = new ArrayDeque<>();
        bfsq.offer(new Pair(sr, sc));
        int[] dir = new int[] {-1, 0, 1, 0, -1};

        while (!bfsq.isEmpty()) {
            Pair curr = bfsq.poll();
            for (int i = 0; i < 4; i++) {
                int r = curr.first + dir[i];
                int c = curr.second + dir[i + 1];
                if (isValidCoordinate(r, c, m, n) && image[r][c] == oldColor)
                    bfsq.offer(new Pair(r, c));
            }
            image[curr.first][curr.second] = color;
        }
        return image;
    }

    private boolean isValidCoordinate(int row, int col, int maxrow, int maxcol) {
        return row > -1 && col > -1 && row < maxrow && col < maxcol;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new FloodFill().floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2)));
    }
}
