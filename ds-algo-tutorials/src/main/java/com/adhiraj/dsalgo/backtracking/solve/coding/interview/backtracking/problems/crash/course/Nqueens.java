package com.adhiraj.dsalgo.backtracking.solve.coding.interview.backtracking.problems.crash.course;

import java.util.ArrayList;
import java.util.List;

public class Nqueens {
    public static void main(String[] args) {

    }

    public List<List<String>> solveNqueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[][] state = new int[n][n];
        search(state, n, result);
        return result;
    }

    private static boolean isValidState(int[][] state, int noOfQueens) {
        for (int i = 0; i < noOfQueens; i++) {
            for (int j = 0; j < noOfQueens; j++) {
                if (state[i][j] == 1)  noOfQueens--;
            }
        }
        return noOfQueens == 0;
    }

    private static int[][][] getCandidates(int[][] state) {
        return new int[0][0][0];
    }

    private static void search(int[][] state, int noOfQueens, List<List<String>> result) {

    }
}

class Pair {
    int first, second;
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
