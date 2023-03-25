package com.adhiraj.dsaalgo.leetcode.week6;

import java.util.ArrayList;
import java.util.List;

public class Lc54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int rmin = 0, rmax = matrix.length - 1, cmin = 0, cmax = matrix[0].length - 1, size = matrix.length * matrix[0].length, i = 0;

        while (i < size) {
            for (int j = cmin; j <= cmax && i < size; j++) {
                res.add(matrix[rmin][j]);
                i++;
            }
            rmin++;

            for (int j = rmin; j <= rmax && i < size; j++) {
                res.add(matrix[j][cmax]);
                i++;
            }
            cmax--;

            for (int j = cmax; j >= cmin && i < size; j--) {
                res.add(matrix[rmax][j]);
                i++;
            }
            rmax--;

            for (int j = rmax; j >= rmin && i < size; j--) {
                res.add(matrix[j][cmin]);
                i++;
            }
            cmin++;
        }

        return res;
    }
}
