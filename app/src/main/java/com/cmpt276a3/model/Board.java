package com.cmpt276a3.model;

public class Board {
    private int width = 6;
    private int height = 4;
    private Cell[][] boardArray;

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
