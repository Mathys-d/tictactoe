package game;

import board.Cell;
import user.ArtificialPlayer;
import user.Opponent;
import user.Player;

public class GameLogic {

    private final InteractionUtilisateur interfaceMenu;
    protected final int gameChoice;

    public GameLogic(InteractionUtilisateur interfaceMenu, int gameChoice) {
        this.interfaceMenu = interfaceMenu;
        this.gameChoice = gameChoice;
    }

    public void usher(int menuChoice, int cpt, Player player, Opponent enemy, ArtificialPlayer ia1, ArtificialPlayer ia2, Cell[][] tableau) {
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

    public boolean isFull(Cell[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String isOwnedBy(int sizX, int sizY, Cell[][] tableau) {
        for (int i = 0; i < sizX; i++) {
            for (int j = 0; j < sizY; j++) {
                return tableau[i][j].getSymbol();
            }
        }
        return "empty";
    }

    public boolean winCondition(int sizeX, int sizeY, Cell[][] tableau) {

        // Horizontal (lignes)
        for (int i = 0; i < sizeX; i++) {
            if (isRowWin(i, sizeY, tableau)) {
                System.out.println("you win");
                return false;
            }
        }

        // Vertical (colonnes)
        for (int j = 0; j < sizeY; j++) {
            if (isColWin(j, sizeX, tableau)) {
                System.out.println("you win");
                return false;
            }
        }

        // Diagonale principale (L-UP TO R-DOWN)
        boolean mainDiag = true;
        String firstSymbol = tableau[0][0].getSymbol();
        if (firstSymbol != null && !firstSymbol.trim().isEmpty() && !firstSymbol.equals(" ")) {
            for (int i = 1; i < sizeX; i++) {
                String symbol = tableau[i][i].getSymbol();
                if (symbol == null || symbol.trim().isEmpty() || !symbol.equals(firstSymbol)) {
                    mainDiag = false;
                    break;
                }
            }
            if (mainDiag) {
                System.out.println("you win");
                return false;
            }
        }

        // Diagonale secondaire (L-DOWN TO R-UP)
        boolean antiDiag = true;
        firstSymbol = tableau[sizeX - 1][0].getSymbol();
        if (firstSymbol != null && !firstSymbol.trim().isEmpty() && !firstSymbol.equals(" ")) {
            for (int i = 1; i < sizeX; i++) {
                String symbol = tableau[sizeX - 1 - i][i].getSymbol();
                if (symbol == null || symbol.trim().isEmpty() || !symbol.equals(firstSymbol)) {
                    antiDiag = false;
                    break;
                }
            }
            if (antiDiag) {
                System.out.println("you win");
                return false;
            }
        }

        // Draw
        if (isFull(tableau)) {
            System.out.println("draw");
            return false;
        }

        return true;
    }

    public boolean isRowWin(int row, int sizeY, Cell[][] tableau) {
        int crossStreak = 0;
        int circleStreak = 0;
        int len = switch (gameChoice) {
            case 1 -> sizeY; // TicTacToe : full row
            case 2 -> 5;     // Gomoku
            case 3 -> 4;     // Puissance 4
            default -> 0;
        };

        for (int i = 0; i < sizeY; i++) {
            String symbol = tableau[row][i].getSymbol();
            if ("X".equals(symbol)) {
                crossStreak++;
                circleStreak = 0;
            } else if ("O".equals(symbol)) {
                circleStreak++;
                crossStreak = 0;
            } else {
                crossStreak = 0;
                circleStreak = 0;
            }

            if (crossStreak == len || circleStreak == len) {
                return true;
            }
        }
        return false;
    }

    public boolean isColWin(int col, int sizeX, Cell[][] tableau) {
        int crossStreak = 0;
        int circleStreak = 0;
        int len = switch (gameChoice) {
            case 1 -> sizeX;
            case 2 -> 5;
            case 3 -> 4;
            default -> 0;
        };

        for (int i = 0; i < sizeX; i++) {
            String symbol = tableau[i][col].getSymbol();
            if ("X".equals(symbol)) {
                crossStreak++;
                circleStreak = 0;
            } else if ("O".equals(symbol)) {
                circleStreak++;
                crossStreak = 0;
            } else {
                crossStreak = 0;
                circleStreak = 0;
            }

            if (crossStreak == len || circleStreak == len) {
                return true;
            }
        }
        return false;
    }

}
