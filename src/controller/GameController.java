package controller;

import model.ArtificialPlayer;
import model.Cell;
import model.Player;
import model.TicTacToe;
import view.InteractionUtilisateur;

import java.util.Arrays;


public  class GameController {

    protected InteractionUtilisateur interfaceMenu;
    protected int gameChoice;
    Player player = new Player();
    Player enemy = new Player();
    ArtificialPlayer ia1 = new ArtificialPlayer();
    ArtificialPlayer ia2 = new ArtificialPlayer();
    GameController gameController;
    TicTacToe ticTacToe;
    int cpt = 0;
    protected int sizeX;
    protected int sizeY;
    Cell[][] tableau;


    public GameController(InteractionUtilisateur interfaceMenu, int gameChoice,int sizeX, int sizeY) {
        this.interfaceMenu = interfaceMenu;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.gameChoice = gameChoice;
        this.ticTacToe = new TicTacToe(gameChoice);
    }

    public void start(int[] gameChoice) {
        this.gameChoice = gameChoice[2];
        if (gameChoice[2] == 1) {
            launchGame(ia1, ia2, sizeX, sizeY);
        }
    }


    public void launchGame(ArtificialPlayer ia1, ArtificialPlayer ia2, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;



        this.tableau = new Cell[sizeX][sizeY];

        this.tableau = TicTacToe.initialise(tableau,sizeX,sizeY);
        int menuChoice = interfaceMenu.startMenu();

        //solo
        if (menuChoice == 1) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                player.setCrossSymbol();
                ia1.setCirclesSymbol();
                game(menuChoice);

            } else {
                player.setCirclesSymbol();
                ia1.setCrossSymbol();
                game(menuChoice);
            }
        }
        //multipalyer
        else if (menuChoice == 2) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                player.setCrossSymbol();
                enemy.setCirclesSymbol();
                game(menuChoice);
            } else {
                player.setCirclesSymbol();
                enemy.setCrossSymbol();
                game(menuChoice);
            }
        } else if (menuChoice == 3) {
            ia1.setCrossSymbol();
            ia2.setCirclesSymbol();
            game(menuChoice);
        }
    }

    public void game(int menuChoice) {

        switch (menuChoice) {
            case 1:
                System.out.println("Solo mode");
                break;
            case 2:
                System.out.println("Multiplayer mode");
                break;
            case 3:
                System.out.println("IA vs IA mode");
                break;
            default:
                System.out.println("Invalid mode");
                return;
        }
        // Boucle principale commune à tous les modes
        while (ticTacToe.isFull(tableau) || ticTacToe.winCondition(sizeX, sizeY, tableau)) {

            usher(menuChoice);
            interfaceMenu.display(sizeX, sizeY, tableau);

            // Cette ligne ne faisait effet que dans le mode IA vs IA
            if (menuChoice == 3) {
                ticTacToe.isOwnedBy(sizeX, sizeY, tableau);
            }
            cpt++;
        }
    }

    public void usher(int menuChoice) {
        Player current;
        int[] move;

        if (menuChoice == 1) {
            current = (cpt % 2 == 0) ? player : ia1;
        } else if (menuChoice == 2) {
            current = (cpt % 2 == 0) ? player : enemy;
        } else {
            current = (cpt % 2 == 0) ? ia1 : ia2;
        }

        if (current instanceof ArtificialPlayer) {
            move = interfaceMenu.getMoveFromArtificial(tableau);
        } else {
            move = interfaceMenu.getMoveFromPlayer(tableau);
        }

        tableau[move[0]][move[1]].setRepresentation(current.getRepresentation());
    }

    public String setGameSymbole() {
        return this.interfaceMenu.setRepresentation();
    }

}
