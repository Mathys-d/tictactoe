package controller;

import model.Player;
import view.InteractionUtilisateur;

public class EnumController {
    protected InteractionUtilisateur interfaceMenu;

    public EnumController() {
    }

    public enum State {
        WAITING,
        PLAYING,
        DRAW,
        WON,
        LOST,
    }

    public int[] getBoard(int gameNb) {
        switch (gameNb) {
            case 1:
                System.out.println("you choose TicTacToe");
                return new int[]{3, 3, 1};
            case 2:
                System.out.println("you choose Gomoku");
                return new int[]{15, 15, 2};
            case 3:
                System.out.println("you choose Power 4");
                return new int[]{6, 7, 1};
        }
        return new int[0];
    }
}
