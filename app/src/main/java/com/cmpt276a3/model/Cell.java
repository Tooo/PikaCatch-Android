package com.cmpt276a3.model;

public class Cell {
    private boolean isClicked = false;
    private boolean hasMine = false;
    private boolean hasScanned = false;
    private int scanNumber = 0;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean hasScanned() {
        return hasScanned;
    }

    public void setHasScanned(boolean hasScanned) {
        this.hasScanned = hasScanned;
    }

    public int getScanNumber() {
        return scanNumber;
    }

    public void setScanNumber(int scanNumber) {
        this.scanNumber = scanNumber;
    }
}
