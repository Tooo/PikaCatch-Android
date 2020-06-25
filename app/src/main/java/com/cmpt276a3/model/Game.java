package com.cmpt276a3.model;

import java.util.Random;

/**
 * Game class holds the main game logic of the game
 * It holds the board and basic game information of mines and scans
 * It handles the logic of adding mines and when cells are clicked
 */
public class Game {
    private Board board;
    private int foundMines = 0;
    private int totalMines;
    private int scansUsed = 0;
    private boolean isGameOver = false;

    public Game(int height, int width, int mines) {
        board = new Board(height, width);
        totalMines = mines;
        addMines();
    }

    private void addMines() {
        Random random = new Random();
        int height = board.getHeight();
        int width = board.getWidth();
        Cell[][] boardArray = board.getBoardArray();
        boolean isValidMine;

        // Randomly place mines, if doesn't have mine
        for (int i = 0; i<totalMines; i++) {
            isValidMine = false;

            while (!isValidMine) {
                int y = random.nextInt(height);
                int x = random.nextInt(width);

                if (!boardArray[y][x].hasMine()) {
                    boardArray[y][x].setHasMine(true);
                    isValidMine = true;
                }
            }
        }
    }

    public void cellClicked(int xScan, int yScan) {
        Cell[][] boardArray = board.getBoardArray();
        Cell scanCell = boardArray[yScan][xScan];
        int height = board.getHeight();
        int width = board.getWidth();

        // Mine found
        if (scanCell.hasMine() && !scanCell.isClicked()) {
            mineFound(xScan, yScan);
            return;
        }

        // Already clicked non-mine or Scanned Mine
        if (scanCell.hasScanned()) {
            return;
        }

        scanCell.setClicked(true);
        scanCell.setHasScanned(true);
        int mineCount = 0;
        scansUsed++;

        // Scan row
        for (int x = 0; x < width; x++) {
            Cell cell = boardArray[yScan][x];
            if (cell.hasMine() && !cell.isClicked()) {
                mineCount++;
            }
        }

        // Scan column
        for (int y = 0; y < height; y++) {
            Cell cell = boardArray[y][xScan];
            if (cell.hasMine() && !cell.isClicked()) {
                mineCount++;
            }
        }

        scanCell.setScanNumber(mineCount);
    }

    private void mineFound(int xScan, int yScan) {
        Cell[][] boardArray = board.getBoardArray();
        Cell scanCell = boardArray[yScan][xScan];
        int height = board.getHeight();
        int width = board.getWidth();

        scanCell.setClicked(true);
        foundMines++;

        // Scan row
        for (int x = 0; x < width; x++) {
            Cell cell = boardArray[yScan][x];
            if (cell.hasScanned()) {
                cell.setScanNumber(cell.getScanNumber()-1);
            }
        }

        // Scan column
        for (int y = 0; y < height; y++) {
            Cell cell = boardArray[y][xScan];
            if (cell.hasScanned()) {
                cell.setScanNumber(cell.getScanNumber()-1);
            }
        }

        if (totalMines == foundMines) {
            isGameOver = true;
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getFoundMines() {
        return foundMines;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public int getScansUsed() {
        return scansUsed;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
