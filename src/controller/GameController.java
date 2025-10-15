package controller;

import model.ArtificialPlayer;
import model.Cell;
import model.Player;
import model.TicTacToe;
import view.InteractionUtilisateur;


public  class GameController {

    protected final InteractionUtilisateur interfaceMenu;
    protected final int gameChoice;
    Player player = new Player();
    TicTacToe ticTacToe;
    int cpt = 0;


    public GameController(InteractionUtilisateur interfaceMenu, int gameChoice) {
        this.interfaceMenu = interfaceMenu;
        this.gameChoice = gameChoice;
        this.ticTacToe = new TicTacToe(gameChoice);
    }

    public void launchGame(Player enemy, ArtificialPlayer ia1, ArtificialPlayer ia2, int sizeX, int sizeY, int whichGame) {
        Cell tab = TicTacToe.init();
        int menuChoice = interfaceMenu.startMenu();
        //solo

        if (menuChoice == 1) {
            String symbolChoice = ticTacToe.setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                ia1.setCirclesSymbol();
                game(menuChoice);
            } else {
                ia1.setCrossSymbol();
                game(menuChoice);
            }
        }
        //multipalyer
        else if (menuChoice == 2) {
            String symbolChoice = ticTacToe.setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                enemy.setCirclesSymbol();
                game(menuChoice);
            } else {
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
        while (!(ticTacToe.isFull(tableau) || !ticTacToe.winCondition(sizeX, sizeY, tableau))) {
            usher(menuChoice, cpt, player, enemy, ia1, ia2, tableau);
            interfaceMenu.display(sizeX, sizeY, tableau);

            // Cette ligne ne faisait effet que dans le mode IA vs IA
            if (menuChoice == 3) {
                ticTacToe.isOwnedBy(sizeX, sizeY, tableau);
            }

            cpt++;
        }
    }

    public void usher(int menuChoice, int cpt, Player player, Player enemy,ArtificialPlayer ia1, ArtificialPlayer ia2, Cell[][] tableau) {
        int[] move;
        String symbol;

        switch (menuChoice) {
            case 1: // Solo
                if (cpt % 2 == 0) { // Tour du joueur
                    System.out.println("Player is playing");
                    move = interfaceMenu.getMoveFromPlayer(tableau);
                    symbol = player.getRepresentation();
                } else { // Tour de l'IA
                    System.out.println("AI is playing");
                    move = interfaceMenu.getMoveFromArtificial(tableau);
                    symbol = ia1.getRepresentation();
                }
                break;

            case 2: // Multiplayer
                if (cpt % 2 == 0) { // Joueur 1
                    System.out.println("Player 1 is playing");
                    move = interfaceMenu.getMoveFromPlayer(tableau);
                    symbol = player.getRepresentation();
                } else { // Joueur 2 / Enemy
                    System.out.println("Player 2 is playing");
                    move = interfaceMenu.getMoveFromPlayer(tableau); // demander au joueur 2
                    symbol = enemy.getRepresentation();
                }
                break;

            case 3: // IA vs IA
                if (cpt % 2 == 0) { // IA1
                    System.out.println("AI 1 is playing");
                    move = interfaceMenu.getMoveFromArtificial(tableau);
                    symbol = ia1.getRepresentation();
                } else { // IA2
                    System.out.println("AI 2 is playing");
                    move = interfaceMenu.getMoveFromArtificial(tableau);
                    symbol = ia2.getRepresentation();
                }
                break;

            default:
                return; // Sécurité
        }

        // Placer le symbole sur le tableau
        int row = move[0];
        int col = move[1];
        tableau[row][col].setRepresentation(symbol);
    }

}
