package com.adhiraj.dsalgo;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame(3, 3, new int[][] {{2, 0}, {0, 0}, {0, 2}, {2, 2}});
// ["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]
        System.out.println("Game Score:" + game.move("D"));
        System.out.println("Game Score:" + game.move("D"));
        System.out.println("Game Score:" + game.move("R"));
        System.out.println("Game Score:" + game.move("U"));
        System.out.println("Game Score:" + game.move("U"));
        System.out.println("Game Score:" + game.move("L"));
        System.out.println("Game Score:" + game.move("D"));
        System.out.println("Game Score:" + game.move("R"));
        System.out.println("Game Score:" + game.move("R"));
        System.out.println("Game Score:" + game.move("U"));
        System.out.println("Game Score:" + game.move("L"));
        System.out.println("Game Score:" + game.move("D"));
    }
}
class SnakeGame {
    GridPos maxGridPos;
    int currLen = 1;
    LinkedHashSet<GridPos> bodyPos = new LinkedHashSet<>();
    Queue<GridPos> foodQueue = new ArrayDeque<>();
    Map<String, Move> moveMap = new HashMap<>();
    boolean isEnded = false;

    public SnakeGame(int width, int height, int[][] food) {
        GridPos currPos = new GridPos(0, 0);
        bodyPos.addLast(currPos);
        maxGridPos = new GridPos(height - 1, width - 1);
        for (int[] foodPos : food) {
            foodQueue.offer(new GridPos(foodPos[0], foodPos[1]));
        }
        // for (GridPos pos : foodQueue)
        //     System.out.println(pos);
        moveMap.put("U", Move.UP);
        moveMap.put("R", Move.RIGHT);
        moveMap.put("D", Move.DOWN);
        moveMap.put("L", Move.LEFT);

    }

    public int move(String direction) {
        if (isEnded) return -1;
        Move currMove = moveMap.get(direction);
        GridPos currGridPos = bodyPos.getLast();
        System.out.println("Current Pos:" + currGridPos);
        System.out.println("Current Move:" + currMove);
        currGridPos = new GridPos(currGridPos.x + currMove.movement.x,
                currGridPos.y + currMove.movement.y);
        System.out.println("New Pos:" + currGridPos);

        if (!isValidGridPosition(currGridPos)) {
            isEnded = true;
            return -1;
        }

        if (isHittingOwnBody(currGridPos)) {
            isEnded = true;
            return -1;
        }

        if (isFoodPresentAtNewGridPosition(currGridPos)) {
            currLen++;
            bodyPos.addLast(currGridPos);
        } else {
            bodyPos.addLast(currGridPos);
            bodyPos.removeFirst();
        }
        System.out.println("Current Length:" + currLen);
        for (GridPos pos : bodyPos)
            System.out.print(pos + ", ");
        System.out.println();
        System.out.println();

        return currLen - 1;
    }

    private boolean isValidGridPosition(GridPos pos) {
        return pos.x >= 0 && pos.x <= maxGridPos.x && pos.y >= 0 && pos.y <= maxGridPos.y;
    }

    private boolean isHittingOwnBody(GridPos pos) {
        for (GridPos pos1 : bodyPos) System.out.print(pos1 + ", ");
        System.out.println("\nNew Pos at check: " + pos);
        return bodyPos.contains(pos) && !pos.equals(bodyPos.getFirst());
    }

    private boolean isFoodPresentAtNewGridPosition(GridPos pos) {
        if (foodQueue.isEmpty()) return false;
        System.out.println("Current Food Pos:" + foodQueue.peek());
        if (pos.equals(foodQueue.peek())) {
            foodQueue.poll();
            return true;
        }
        return false;
    }
}

enum Move {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    GridPos movement;
    Move(int x, int y) {
        movement = new GridPos(x, y);
    }
}

class GridPos {
    int x;
    int y;

    GridPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof GridPos)) return false;
        GridPos other = (GridPos) o;
        if (other.x != this.x || other.y != this.y) return false;
        return true;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */