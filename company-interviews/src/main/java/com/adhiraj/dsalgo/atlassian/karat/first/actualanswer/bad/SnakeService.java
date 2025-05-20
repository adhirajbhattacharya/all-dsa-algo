package com.adhiraj.dsalgo.atlassian.karat.first.actualanswer.bad;

import java.util.Queue;

public interface SnakeService {

    int moveSnake(Queue<int[]> body, MoveDirection direction, int moveCount);//dir

    boolean isGameOver(Queue<int[]> body);
}
