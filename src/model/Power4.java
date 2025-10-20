package model;

import controller.GameInterface;

public class Power4 implements GameInterface {

    private final int gameChoice;

    public Power4(int gameChoice) {
        this.gameChoice = gameChoice;
    }

    public static void initialise(Cell[][] tableau, int sizeX, int sizeY) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (tableau[i][j] == null) {
                    tableau[i][j] = new Cell();
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

    public boolean winCondition(int sizeX, int sizeY, Cell[][] tableau, int winLength) {
        // Horizontal (lignes)
        for (int i = 0; i < sizeX; i++) {
            if (isRowWin(i, sizeY, tableau, winLength)) {
                return true;
            }
        }

        // Vertical (colonnes)
        for (int j = 0; j < sizeY; j++) {
            if (isColWin(j, sizeX, tableau,winLength)) {
                return true;
            }
        }

        // Diagonale principale (L-UP TO R-DOWN)
        boolean mainDiag = true;
        String firstSymbol = tableau[0][0].getSymbol();

        if (firstSymbol != null && !firstSymbol.trim().isEmpty() && !firstSymbol.equals(" ")) {
            for (int i = 1; i < winLength && i < sizeX && i < sizeY; i++) {
                String symbol = tableau[i][i].getSymbol();
                if (symbol == null || symbol.trim().isEmpty() || !symbol.equals(firstSymbol)) {
                    mainDiag = false;
                    break;
                }
            }
            if (mainDiag) {
                return true;
            }
        }

        // Diagonale secondaire (L-DOWN TO R-UP)
        boolean antiDiag = true;
        firstSymbol = tableau[sizeX - 1][0].getSymbol();
        if (firstSymbol != null && !firstSymbol.trim().isEmpty() && !firstSymbol.equals(" ")) {
            for (int i = 1; i < winLength && i < sizeX && i < sizeY; i++) {
                String symbol = tableau[sizeX - 1 - i][i].getSymbol();
                if (symbol == null || symbol.trim().isEmpty() || !symbol.equals(firstSymbol)) {
                    antiDiag = false;
                    break;
                }
            }
            if (antiDiag) {
                return true;
            }
        }

        if (isFull(tableau)) {
            return false;
        }

        return false;
    }

    public boolean isRowWin(int row, int sizeY, Cell[][] tableau,int winLength) {
        int crossStreak = 0;
        int circleStreak = 0;
        int len = switch (gameChoice) {
            case 1 -> sizeY; // TicTacToe : full row
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

            if (crossStreak == winLength || circleStreak == winLength) {
                return true;
            }
        }
        return false;
    }

    public boolean isColWin(int col, int sizeX, Cell[][] tableau,int winLength) {
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

            if (crossStreak == winLength || circleStreak == winLength) {
                return true;
            }
        }
        return false;
    }


}