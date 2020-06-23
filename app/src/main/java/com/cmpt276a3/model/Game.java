package com.cmpt276a3.model;

import java.util.Random;

public class Game {
    private Board board = new Board();
    private int foundMines = 0;
    private int totalMines = 6;
    private int scansUsed = 0;

    public Game() {
        addMines();
    }

    private void addMines() {
        Random random = new Random();
        int height = board.getHeight();
        int width = board.getWidth();
        Cell[][] boardArray = board.getBoardArray();
        boolean isValidMine;

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
}
