package com.cmpt276a3.model;

public class Board {
    private int width = 6;
    private int height = 4;
    private Cell[][] boardArray = new Cell[height][width];

    public Board () {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardArray[y][x] = new Cell();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Cell[][] getBoardArray() {
        return boardArray;
    }

    public void setBoardArray(Cell[][] boardArray) {
        this.boardArray = boardArray;
    }
}