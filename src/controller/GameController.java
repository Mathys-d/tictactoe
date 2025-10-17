package controller;

import model.ArtificialPlayer;
import view.InteractionUtilisateur;


public abstract class GameController {
    protected InteractionUtilisateur interfaceMenu;
    protected int gameChoice;
    protected int sizeX;
    protected int sizeY;

    public GameController(InteractionUtilisateur interfaceMenu, int gameChoice,int sizeX, int sizeY) {
        this.interfaceMenu = interfaceMenu;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.gameChoice = gameChoice;
    }

    public void start(int[] gameChoice) {}

    public void launchGame(ArtificialPlayer ia1, ArtificialPlayer ia2, int sizeX, int sizeY) {}

    public void game(int menuChoice) {}

    public void usher(int menuChoice) {}

    public String setGameSymbole() {
        return "";
    }
}