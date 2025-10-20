package controller;

import model.Cell;

public interface GameInterface {
    boolean winCondition(int sizeX, int sizeY, Cell[][] tableau);
    boolean isFull(Cell[][] tableau);
    boolean isRowWin(int row, int sizeY, Cell[][] tableau);
    boolean isColWin(int col, int sizeX, Cell[][] tableau);
    }
