package com.adhiraj.algorithmsi.week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
  
  private final Node solutionNode;
  
  private class Node implements Comparable<Node> {
    private final Board board;
    private final Node prev;
    private final int moves;
    
    private Node(Board board, Node prev, int moves) {
      super();
      this.board = board;
      this.prev = prev;
      this.moves = moves;
    }
    
    @Override
    public int compareTo(Node n) {
      int p1 = board.manhattan() + moves;
      int p2 = n.board.manhattan() + n.moves;
      int cmp = Integer.compare(p1, p2);
      if (cmp == 0) {
        if (board.isGoal())
          return -1;
        if (n.board.isGoal())
          return 1;
        return Integer.compare(moves, n.moves);
      }
      return cmp;
    }
  }
  
  public Solver(Board initial) {
    if (initial == null) {
      throw new NullPointerException();
    }
    Node n = new Node(initial, null, 0);
    MinPQ<Node> queue = new MinPQ<>();
    queue.insert(n);
    n = new Node(initial.twin(), null, 0);
    MinPQ<Node> twinQueue = new MinPQ<>();
    twinQueue.insert(n);
    solutionNode = solve(queue, twinQueue);
  }
  
  private Node solve(MinPQ<Node> queue, MinPQ<Node> twinQueue) {
    while (!queue.min().board.isGoal() && !twinQueue.min().board.isGoal()) {
      Node n1 = queue.delMin();
      int m1 = n1.moves + 1;
      Node n2 = twinQueue.delMin();
      int m2 = n2.moves + 1;
      for (Board b : n1.board.neighbors()) {
        if (n1.prev != null && b.equals(n1.prev.board))
          continue;
        Node n = new Node(b, n1, m1);
        queue.insert(n);
      }
      for (Board b : n2.board.neighbors()) {
        if (n2.prev != null && b.equals(n2.prev.board))
          continue;
        Node n = new Node(b, n2, m2);
        twinQueue.insert(n);
      }
    }
    return queue.min().board.isGoal() ? queue.min() : null;
  }
  
  public boolean isSolvable() {
    return solutionNode != null;
  }
  
  public int moves() {
    return solutionNode == null ? -1 : solutionNode.moves;
  }
  
  public Iterable<Board> solution() {
    if (solutionNode == null)
      return null;
    ResizingArrayStack<Board> stack = new ResizingArrayStack<>();
    Node t = solutionNode;
    while (t != null) {
      stack.push(t.board);
      t = t.prev;
    }
    return stack;
  }
  
  public static void main(String[] args) {
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);
    // solve the puzzle
    Solver solver = new Solver(initial);
    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
  
}
