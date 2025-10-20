package controller;

import model.Cell;
import model.Player;

public interface GameInterface {
/*
    Player getPlayer1(); //a implementer
    Player getPlayer2(); //a implementer
    Cell[][] getTableau(); //a implementer

    void setPlayer1(Player p1); //a implementer
    void setPlayer2(Player p2); //a implementer
    void setTableau(Cell[][] tab); //a implementer
*/
    boolean winCondition(int sizeX, int sizeY, Cell[][] tableau, int winLength);
    boolean isFull(Cell[][] tableau);
    boolean isRowWin(int row, int sizeY, Cell[][] tableau, int winLength);
    boolean isColWin(int col, int sizeX, Cell[][] tableau ,int winLength);
}