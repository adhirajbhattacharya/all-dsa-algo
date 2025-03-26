package com.adhiraj.dsalgo.backtracking.solve.coding.interview.backtracking.problems.crash.course;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] input = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        new SudokuSolver().solveSudoku(input);
    }

    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0);
    }

    public boolean solveSudoku(char[][] board, int idx) {
        // all conditions are already verified in below. reaching here means solution.
        if (idx >= 81) return true;

        int row = idx / 9;
        int col = idx % 9;

        boolean isDone = false;
        if (Character.isDigit(board[row][col])) {
            isDone = solveSudoku(board, idx + 1);
        }

        if (isDone) return true;

        for (int i = 1; i < 10; i++) {
            // check all constraints
            if (!isValid(board, row, col, i)) continue;

            board[row][col] = Character.forDigit(i, 10);
            isDone = solveSudoku(board, idx + 1);

            if (isDone) return true;
            board[row][col] = '.';
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, int c) {
        for (int i = 0; i < 9; i++) {
            if (Character.getNumericValue(board[i][col]) == c)
                return false;
            if (Character.getNumericValue(board[row][i]) == c)
                return false;
        }

        int gridSize = 3;
        int startX = row - row % gridSize;
        int startY = col - col % gridSize;
        for (int i = startX; i < startX + gridSize; i++) {
            for (int j = startY; j < startY + gridSize; j++) {
                if (Character.getNumericValue(board[i][j]) == c) {
                    return false;
                }
            }
        }

        return true;
    }

}
