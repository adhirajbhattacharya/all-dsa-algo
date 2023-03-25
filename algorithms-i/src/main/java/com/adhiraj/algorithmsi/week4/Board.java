package com.adhiraj.algorithmsi.week4;

import edu.princeton.cs.algs4.ResizingArrayQueue;

public class Board {
  
  private static final int SPACE = 0;
  
  private final int[][] blocks;
  private final int dimension;
  private int hamming;
  private int manhattan;
  
  public Board(int[][] blocks) {
    if (blocks == null)
      throw new IllegalArgumentException();
    int row = blocks.length;
    int col = blocks[0].length;
    if (row != col || row == 1)
      throw new IllegalArgumentException();
    dimension = row;
    this.blocks = new int[dimension][dimension];
    boolean zeroAbsent = true;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        this.blocks[i][j] = blocks[i][j];
        if (isSpace(i, j))
          zeroAbsent = false;
      }
    }
    if (zeroAbsent)
      throw new IllegalArgumentException();
    hamming = calculateHamming();
    manhattan = calculateManhattan();
  }
  
  private int calculateHamming() {
    int h = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (!isSpace(i, j)) {
          int t = i * dimension + j + 1;
          if (t != blocks[i][j])
            h++;
        }
      }
    }
    return h;
  }
  
  private int calculateManhattan() {
    int m = 0;
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        if (!isSpace(i, j)) {
          int t = blocks[i][j] - 1;
          int row = t / dimension;
          int col = t % dimension;
          m += (row - i) > 0 ? (row - i) : (i - row);
          m += (col - j) > 0 ? (col - j) : (j - col);
        }
      }
    }
    return m;
  }
  
  private void refreshHammingAndManhattan() {
    hamming = calculateHamming();
    manhattan = calculateManhattan();
  }
  
  public int dimension() {
    return dimension;
  }
  
  public int hamming() {
    return hamming;
  }
  
  public int manhattan() {
    return manhattan;
  }
  
  public boolean isGoal() {
    return hamming == 0 && manhattan == 0;
  }
  
  public Board twin() {
    Board b = new Board(blocks);
    ResizingArrayQueue<Integer> queue = new ResizingArrayQueue<>();
    for (int i = 0; i < dimension && queue.size() < 4; i++) {
      for (int j = 0; j < dimension; j++) {
        if (isSpace(i, j))
          continue;
        queue.enqueue(i);
        queue.enqueue(j);
      }
    }
    exch(b.blocks, queue.dequeue(), queue.dequeue(), queue.dequeue(), queue.dequeue());
    b.refreshHammingAndManhattan();
    return b;
  }
  
  public Iterable<Board> neighbors() {
    ResizingArrayQueue<Board> queue = new ResizingArrayQueue<>();
    int row = -1;
    int col = -1;
    for (int i = 0; i < dimension && (row == -1 || col == -1); i++) {
      for (int j = 0; j < dimension; j++) {
        if (isSpace(i, j)) {
          row = i;
          col = j;
          break;
        }
      }
    }
    int rowUp = row - 1;
    int rowDown = row + 1;
    int colLeft = col - 1;
    int colRight = col + 1;
    if (isIndexValid(rowUp, col)) {
      Board b = new Board(blocks);
      exch(b.blocks, row, col, rowUp, col);
      b.refreshHammingAndManhattan();
      queue.enqueue(b);
    }
    if (isIndexValid(rowDown, col)) {
      Board b = new Board(blocks);
      exch(b.blocks, row, col, rowDown, col);
      b.refreshHammingAndManhattan();
      queue.enqueue(b);
    }
    if (isIndexValid(row, colLeft)) {
      Board b = new Board(blocks);
      exch(b.blocks, row, col, row, colLeft);
      b.refreshHammingAndManhattan();
      queue.enqueue(b);
    }
    if (isIndexValid(row, colRight)) {
      Board b = new Board(blocks);
      exch(b.blocks, row, col, row, colRight);
      b.refreshHammingAndManhattan();
      queue.enqueue(b);
    }
    return queue;
  }
  
  private boolean isIndexValid(int row, int col) {
    return !(row < 0 || row >= dimension || col < 0 || col >= dimension);
  }
  
  private static void exch(int[][] a, int row, int col, int newRow, int newCol) {
    int tmp = a[row][col];
    a[row][col] = a[newRow][newCol];
    a[newRow][newCol] = tmp;
  }
  
  public boolean equals(Object y) {
    if (y == null)
      return false;
    if (this == y)
      return true;
    if (this.getClass() != y.getClass())
      return false;
    Board b = (Board) y;
    if (this.dimension != b.dimension())
      return false;
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.dimension; j++) {
        if (this.blocks[i][j] != b.blocks[i][j])
          return false;
      }
    }
    return true;
  }
  
  private boolean isSpace(int i, int j) {
    return blocks[i][j] == SPACE;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    sb.append(dimension);
    for (int i = 0; i < this.dimension; i++) {
      sb.append("\n");
      for (int j = 0; j < this.dimension; j++) {
        sb.append(" ");
        if (!isSpace(i, j))
          sb.append(blocks[i][j]);
        else
          sb.append(SPACE);
      }
    }
    return sb.toString();
  }
  
  public static void main(String[] args) {
    // unit tests (not graded)
  }
  
}
