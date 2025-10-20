package controller;

import view.InteractionUtilisateur;


public abstract class GameController {
    protected InteractionUtilisateur interfaceMenu;
    protected int gameChoice;
    protected int winLength;
    protected int sizeX;
    protected int sizeY;

    public GameController(InteractionUtilisateur interfaceMenu,int sizeX,int sizeY,int gameChoice) {
        this.interfaceMenu = interfaceMenu;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.gameChoice = gameChoice;
        this.winLength = gameChoice;
    }

    public void start(int[] gameChoice) {}

    public void launchGame(int sizeX, int sizeY) {}

    public void game(int menuChoice) {}

    public void usher(int menuChoice) {}

    public String setGameSymbole() {
        return "";
    }
}