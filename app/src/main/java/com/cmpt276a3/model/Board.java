package com.cmpt276a3.model;

public class Board {
    private int width;
    private int height;
    private Cell[][] boardArray;

    public Board (int height, int width) {
        this.height = height;
        this.width = width;
        boardArray = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boardArray[y][x] = new Cell();
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getBoardArray() {
        return boardArray;
    }

}
