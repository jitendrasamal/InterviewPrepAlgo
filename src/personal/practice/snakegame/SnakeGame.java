package personal.practice.snakegame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/*
https://leetcode.com/problems/design-snake-game/description/
 */
class SnakeGame {
    int[][] food;
    int foodEatenCount = 0;
    int width;
    int height;
    Set<Position> snakePositionsSet = new HashSet<>();
    LinkedList<Position> snakePositionList = new LinkedList<>();

    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.height = height;
        this.width = width;
        snakePositionsSet.add(new Position(0, 0));
        snakePositionList.add(new Position(0, 0));
    }

    public int move(String direction) {
        Position newHead = getNewHeadPosition(direction);
        if (isOutOfBound(newHead)) {
            return -1;
        }
        boolean isFood = isFoodInPos(newHead);
        if (!isFood) {
            removeTail();
        } else {
            ++foodEatenCount;
        }
        if (isBitingSelf(newHead)) {
            return -1;
        }
        addHead(newHead);
        return foodEatenCount;
    }

    private Position getNewHeadPosition(String direction) {
        int c = 0;
        int r = 0;
        switch (direction) {
            case "R":
                c = 1;
                break;
            case "L":
                c = -1;
                break;
            case "U":
                r = -1;
                break;
            case "D":
                r = 1;
                break;
        }
        Position currentHead = snakePositionList.getFirst();
        return new Position(currentHead.row + r, currentHead.col + c);
    }

    private boolean isOutOfBound(Position p) {
        return p.col < 0 || p.row < 0 || p.row >= height || p.col >= width;
    }

    private boolean isFoodInPos(Position p) {
        return foodEatenCount < food.length && p.row == food[foodEatenCount][0] && p.col == food[foodEatenCount][1];
    }

    private void removeTail() {
        Position p = snakePositionList.getLast();
        snakePositionList.removeLast();
        snakePositionsSet.remove(p);
    }

    private boolean isBitingSelf(Position newHead) {
        return snakePositionsSet.contains(newHead);
    }

    private void addHead(Position p) {
        snakePositionList.addFirst(p);
        snakePositionsSet.add(p);
    }

    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.col = col;
            this.row = row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }
    }
}
