package model;

import controller.GameController;
import view.InteractionUtilisateur;


public class TicTacToe {
    private final int gameChoice;
    protected int sizeX;
    protected int sizeY;
    Cell[][] tableau;
    Player enemy = new Player();
    InteractionUtilisateur interfaceMenu;
    ArtificialPlayer ia1 = new ArtificialPlayer();
    ArtificialPlayer ia2 = new ArtificialPlayer();
    GameController gameController;

    public TicTacToe(int gameChoice) {
        this.gameChoice = gameChoice;
    }

    public void start(int[] gameChoice) {
        this.sizeX = gameChoice[0];
        this.sizeY = gameChoice[1];
        this.tableau = new Cell[sizeX][sizeY];
        this.interfaceMenu = new InteractionUtilisateur(tableau, gameChoice[2]);

        if (gameChoice[2] == 1) {
            gameController.launchGame(enemy, ia1, ia2, sizeX, sizeY, gameChoice[2]);
        } else if (gameChoice[2] == 2) {
            gameController.launchGame(enemy, ia1, ia2, sizeX, sizeY, gameChoice[2]);
        } else if (gameChoice[2] == 3) {
            gameController.launchGame(enemy, ia1, ia2, sizeX, sizeY, gameChoice[2]);
        }
    }



    public Cell init() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (tableau[i][j] == null) {
                    return tableau[i][j] = new Cell();
                }
            }
        }
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


    public void startGame() {

    }
    
    
    
    
    
    
    
    public String setGameSymbole() {
        return this.interfaceMenu.setRepresentation();
    }
}