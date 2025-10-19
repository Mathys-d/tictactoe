package controller;

import model.*;
import view.InteractionUtilisateur;

public class MyGameController extends GameController {
    GameInterface game;

    Player player1;
    Player player2;
    int cpt = 0;
    Cell[][] tableau;


    public MyGameController(InteractionUtilisateur interfaceMenu,int sizeX,int sizeY,int gameChoice) {
        super(interfaceMenu, sizeX, sizeY, gameChoice);
        if (gameChoice == 1) {
            game = new TicTacToe(gameChoice);
        } else if (gameChoice == 2) {
            game = new Gomoku(gameChoice);
        } else if (gameChoice == 3) {
            game = new Power4(gameChoice);
        }


    }

    @Override
    public void start(int[] gameChoice) {
        this.gameChoice = gameChoice[2];
        launchGame(gameChoice[0], gameChoice[1]);
    }

    @Override
    public void launchGame(int sizeX, int sizeY) {
        this.tableau = new Cell[sizeX][sizeY];

        if (gameChoice == 1) {
            TicTacToe.initialise(tableau, sizeX, sizeY);
        }else if (gameChoice == 2) {
            Gomoku.initialise(tableau, sizeX, sizeY);
        }else if (gameChoice == 3) {
            Power4.initialise(tableau, sizeX, sizeY);
        }

        int menuChoice = interfaceMenu.startMenu();
        initPlayer(menuChoice);

        //solo
        if (menuChoice == 1) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                player1.setCrossSymbol();
                player2.setCirclesSymbol();
                game(menuChoice);
            } else {
                player1.setCirclesSymbol();
                player2.setCrossSymbol();
                game(menuChoice);
            }
        }
        //multipalyer
        else if (menuChoice == 2) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                player1.setCrossSymbol();
                player2.setCirclesSymbol();
                game(menuChoice);
            } else {
                player1.setCirclesSymbol();
                player2.setCrossSymbol();
                game(menuChoice);
            }
        } else if (menuChoice == 3) {
            player1.setCrossSymbol();
            player2.setCirclesSymbol();
            game(menuChoice);
        }
    }

    @Override
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
        while (game.isFull(tableau) && game.winCondition(sizeX, sizeY, tableau)) {
            System.out.println("INSIDE THE LOOP ");

            usher(menuChoice);
            interfaceMenu.display(sizeX, sizeY, tableau);
            cpt++;
            if (!game.isFull(tableau)){
                System.out.println(GameStateController.PLAYING);
            }else if (game.isFull(tableau)) {
                System.out.println(GameStateController.DRAW);
            }
        }
    }

    @Override
    public void usher(int menuChoice) {
        Player current;
        int[] move;

        current = (cpt % 2 == 0) ? player1 : player2;

        if (current instanceof ArtificialPlayer) {
            move = interfaceMenu.getMoveFromArtificial(tableau, current);
        } else {
            move = interfaceMenu.getMoveFromPlayer(tableau, current);
        }
        tableau[move[0]][move[1]].setRepresentation(current.getRepresentation());
    }

    public void initPlayer(int menuChoice) {
        switch (menuChoice) {
            case 1:
                this.player1 = new Player();
                this.player2 = new ArtificialPlayer();
                break;
            case 2:
                this.player1 = new Player();
                this.player2 = new Player();
                break;
            case 3:
                this.player1 = new ArtificialPlayer();
                this.player2 = new ArtificialPlayer();
                break;
        }
    }

    @Override
    public String setGameSymbole() {
        return this.player1.setRepresentation();
    }
}
