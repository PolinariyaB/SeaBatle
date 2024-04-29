package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Cell> shipCells;

    public Ship() {

        shipCells = new ArrayList<>();
    }

    public void addCell(Cell cell) {

        shipCells.add(cell);
    }
    //1

    public boolean isSunk() {
        for (Cell cell : shipCells) {
            if (!cell.isShot()) {
                return false;
            }
        }
        return true;
    }

    public List<Cell> getShipCells() {
        return shipCells;
    }
}