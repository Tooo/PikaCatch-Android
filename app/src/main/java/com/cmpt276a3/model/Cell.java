package com.cmpt276a3.model;

public class Cell {
    private boolean hasMine;
    private int scanNumber;

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public int getScanNumber() {
        return scanNumber;
    }

    public void setScanNumber(int scanNumber) {
        this.scanNumber = scanNumber;
    }
}
