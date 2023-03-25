package com.adhiraj.algorithmsi.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private static final String ILLEGAL_ARGUMENTS_MESSAGE = "Index out of bounds";
  private final WeightedQuickUnionUF unionfindBackwash;
  private final WeightedQuickUnionUF unionfind;
  private final boolean[] opened;
  private int openSites;
  private final int size;
  private final int top;
  private final int bottom;

  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Illegal size");
    }
    top = 0;
    bottom = n * n + 1;
    unionfind = new WeightedQuickUnionUF(bottom);
    unionfindBackwash = new WeightedQuickUnionUF(bottom + 1);
    opened = new boolean[bottom];
    size = n;
  }

  public boolean isOpen(int row, int col) {
    if (!isIndexValid(row, col)) {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_MESSAGE);
    }
    return opened[get1dIndex(row, col)];
  }

  public boolean isFull(int row, int col) {
    if (!isIndexValid(row, col)) {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_MESSAGE);
    }
    return unionfind.connected(top, get1dIndex(row, col));
  }

  public void open(int row, int col) {
    if (!isIndexValid(row, col)) {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_MESSAGE);
    }
    if (isOpen(row, col)) {
      return;
    }
    opened[get1dIndex(row, col)] = true;
    openSites++;

    connectToVirtualTop(row, col);
    connectToVirtualBottom(row, col);
    connectUpNode(row, col);
    connectLeftNode(row, col);
    connectDownNode(row, col);
    connectRightNode(row, col);
  }

  private void connectUpNode(int row, int col) {
    int rowUp = row - 1;
    if (isIndexValid(rowUp, col) && isOpen(rowUp, col)) {
      unionfindBackwash.union(get1dIndex(row, col), get1dIndex(rowUp, col));
      unionfind.union(get1dIndex(row, col), get1dIndex(rowUp, col));
    }
  }

  private void connectLeftNode(int row, int col) {
    int colLeft = col - 1;
    if (isIndexValid(row, colLeft) && isOpen(row, colLeft)) {
      unionfindBackwash.union(get1dIndex(row, col), get1dIndex(row, colLeft));
      unionfind.union(get1dIndex(row, col), get1dIndex(row, colLeft));
    }
  }

  private void connectDownNode(int row, int col) {
    int rowDown = row + 1;
    if (isIndexValid(rowDown, col) && isOpen(rowDown, col)) {
      unionfindBackwash.union(get1dIndex(row, col), get1dIndex(rowDown, col));
      unionfind.union(get1dIndex(row, col), get1dIndex(rowDown, col));
    }
  }

  private void connectRightNode(int row, int col) {
    int colRight = col + 1;
    if (isIndexValid(row, colRight) && isOpen(row, colRight)) {
      unionfindBackwash.union(get1dIndex(row, col), get1dIndex(row, colRight));
      unionfind.union(get1dIndex(row, col), get1dIndex(row, colRight));
    }
  }

  private void connectToVirtualTop(int row, int col) {
    if (row == 1) {
      unionfindBackwash.union(get1dIndex(row, col), top);
      unionfind.union(get1dIndex(row, col), top);
    }
  }

  private void connectToVirtualBottom(int row, int col) {
    if (row == size) {
      unionfindBackwash.union(get1dIndex(row, col), bottom);
    }
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    return unionfindBackwash.connected(top, bottom);
  }

  private int get1dIndex(int row, int col) {
    return (row - 1) * size + col;
  }

  private boolean isIndexValid(int row, int col) {
    return !(row < 1 || row > size || col < 1 || col > size);
  }

  public static void main(String[] args) {
    // optinal test client
  }

}
