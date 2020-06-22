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
        boolean isValidMine = false;

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

    public void scanMines(int xScan, int yScan) {
        Cell[][] boardArray = board.getBoardArray();
        Cell scanCell = boardArray[yScan][xScan];
        int height = board.getHeight();
        int width = board.getWidth();

        if (scanCell.hasMine() && !scanCell.isClicked()) {
            foundMines++;
            scanCell.setClicked(true);

            // Scan row
            for (int x = 0; x < width; x++) {
                Cell cell = boardArray[yScan][x];
                if (!cell.hasMine() && cell.isClicked()) {
                    cell.setScanNumber(cell.getScanNumber()-1);
                }
            }

            // Scan column
            for (int y = 0; y < height; y++) {
                Cell cell = boardArray[y][xScan];
                if (!cell.hasMine() && cell.isClicked()) {
                    cell.setScanNumber(cell.getScanNumber()-1);
                }
            }

            return;
        }
        int mineCount = 0;

        scansUsed++;
        scanCell.setClicked(true);

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
