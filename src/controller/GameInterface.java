package controller;

public interface GameInterface {
    boolean winCondition(int sizeX, int sizeY, Cell[][] tableau);
    boolean isFull(Cell[][] tableau);
    // tu peux ajouter d’autres méthodes communes si nécessaire
}
