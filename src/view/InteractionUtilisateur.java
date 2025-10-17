package view;

import model.Cell;
import model.Player;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InteractionUtilisateur {

    protected final Cell[][] tableau;
    Scanner sc = new Scanner(System.in);
    Random randomNumbers = new Random();
    private final int whichGame;

    public InteractionUtilisateur(Cell[][] tableau, int whichGame) {
        this.tableau = tableau;
        this.whichGame = whichGame;
    }

    public int startMenu() {
        boolean a = false;
        while (!a) {
            System.out.println("choose your mode (solo vs ia (1)/ multiplayer (2) / ia vs ia (3))");
            String choice = sc.nextLine();
            int option;
            try {
                option = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("wrong input (solo vs ia (1)/ multiplayer (2))");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("you choose solo vs ia");
                    a = true;
                    return 1;
                case 2:
                    System.out.println("you choose multiplayer");
                    a = true;
                    return 2;
                case 3:
                    System.out.println("you choose ia vs ia");
                    a = true;
                    return 3;
                default:
                    System.out.println("wrong input (solo vs ia (1)/ multiplayer (2) / ia vs ia (3))");
            }
        }
        return 0;
    }

    public void display(int sizX, int sizeY, Cell[][] tableau) {
        for (int i = 0; i < sizX; i++) {
            displayRow(sizX);
            System.out.print("| ");
            for (int j = 0; j < sizeY; j++) {
                if (tableau[i][j] == null) {
                    System.out.print(" " + " | ");
                } else {
                    System.out.print(tableau[i][j] + " | ");
                }
            }
            System.out.println();
        }
        displayRow(sizX);
    }

    public boolean isUsed(int i, int j, Cell[][] tableau) {
        return !tableau[i][j].isEmpty();
    }

    public void displayRow(int size) {
        int correction = 0;
        if (size == 3) {
            correction = 2;
        } else if (size == 7) {
            correction = 12;
        } else if (size == 15) {
            correction = 14;
        }
        for (int j = 0; j < (size * 7) - correction; j++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public int lastCellRow(int col) {
        for (int row = tableau.length - 1; row >= 0; row--) {
            if (!isUsed(row, col, tableau)) {
                return row;
            }
        }
        return -1;
    }

    public int[] getMoveFromPlayer(Cell[][] tableau) {
        int maxRow = tableau.length - 1;
        int maxCol = tableau[0].length - 1;

        while (true) {
            try {
                System.out.println("Choose the row [0-" + maxRow + "]: ");
                int row = Integer.parseInt(sc.nextLine().trim());
                System.out.println("Choose the column [0-" + maxCol + "]: ");
                int col = Integer.parseInt(sc.nextLine().trim());
                System.out.println("You chose: (" + row + ", " + col + ")");

                if (row < 0 || row > maxRow || col < 0 || col > maxCol) {
                    System.out.println("Coordinates out of range. Try again.");
                    continue;
                }

                if (isUsed(row, col, tableau)) {
                    System.out.println("Cell already used. Try again.");
                    continue;
                }

                return new int[]{row, col};

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values only.");
            }
        }
    }

    public int[] getMoveFromArtificial(Cell[][] tableau) {
        int row, col;
        if (whichGame == 3) {
            do {
                col = randomNumbers.nextInt(tableau[0].length);
                row = lastCellRow(col);
            } while (row == -1);
        } else {
            do {
                row = randomNumbers.nextInt(tableau.length);
                col = randomNumbers.nextInt(tableau[0].length);
            } while (isUsed(row, col, tableau));
        }

        System.out.println("IA chooses " + row + " and " + col);
        return new int[]{row, col};
    }


}