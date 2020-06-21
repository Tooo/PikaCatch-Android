package com.cmpt276a3.model;

public class Game {
    private Board board;
    private int foundMines;
    private int totalMines;
    private int scansUsed;

    public Game() {

    }

    public Board getBoard() {
        return board;
    }

    public int getFoundMines() {
        return foundMines;
    }

    public void setFoundMines(int foundMines) {
        this.foundMines = foundMines;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public void setTotalMines(int totalMines) {
        this.totalMines = totalMines;
    }

    public int getScansUsed() {
        return scansUsed;
    }

    public void setScansUsed(int scansUsed) {
        this.scansUsed = scansUsed;
    }
}
