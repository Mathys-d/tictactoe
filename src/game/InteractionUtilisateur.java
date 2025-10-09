package game;

import board.Cell;

import java.util.Random;
import java.util.Scanner;

public class InteractionUtilisateur {

    public InteractionUtilisateur( ) {
    }

    Scanner sc = new Scanner(System.in);
    Random randomNumbers = new Random();

    public int [] chooseGame(){
        boolean a = false;
        int [] choiceListe = new int[3];
        while (!a) {
            System.out.println("choose your game (TicTacToe (1)/ gumoku (2) / power 4 (3))");
            String choice = sc.nextLine();
            if (Integer.parseInt(choice) == 1) {
                System.out.println("you choose TicTacToe");
                a = true;
                choiceListe[0] = 3; //size
                choiceListe[1] = 3; //size y
                choiceListe[2] = 1; //which game
                return choiceListe;
            } else if (Integer.parseInt(choice) == 2) {
                System.out.println("you choose gumoku");
                a = true;
                choiceListe[0] = 15; //size
                choiceListe[1] = 15; //size y
                choiceListe[2] = 2; //which game
                return choiceListe;
            } else if (Integer.parseInt(choice) == 3) {
                System.out.println("you choose power 4");
                a = true;
                choiceListe[0] = 7; //size x
                choiceListe[1] = 6; //size y
                choiceListe[2] = 3; //which game
                return choiceListe;
            } else {
                System.out.println("wrong input (solo vs ia (1)/ multiplayer (2))");
            }
        }
        return choiceListe;
    }

    protected int startMenu() {
        boolean a = false;
        while (a == false) {
            System.out.println("choose your mode (solo vs ia (1)/ multiplayer (2) / ia vs ia (3))");
            String choice = sc.nextLine();
            if (Integer.parseInt(choice) == 1) {
                System.out.println("you choose solo vs ia");
                a = true;
                return 1;
            } else if (Integer.parseInt(choice) == 2) {
                System.out.println("you choose multiplayer");
                a = true;
                return 2;
            } else if (Integer.parseInt(choice) == 3) {
                System.out.println("you choose ia vs ia");
                a = true;
                return 3;
            } else {
                System.out.println("wrong input (solo vs ia (1)/ multiplayer (2))");
            }
        }
        return 0;
    }

    public void display(int sizX,int sizeY, Cell[][] tableau) {
        for (int i = 0; i < sizX; i++) {
            System.out.println("-------------------");
            System.out.print("| ");

            for (int j = 0; j < sizeY; j++) {
                if (tableau[i][j] == null) {
                    System.out.print("   " + " | ");
                } else {
                    System.out.print(tableau[i][j] + " | ");
                }
            }
            System.out.println();

        }
        System.out.println("------------------");
    }

    public boolean isUsed(int i, int j, Cell[][] tableau) {
        if (!tableau[i][j].isEmpty()) {
            return true;
        } return false;
    }

    public int[] getMoveFromPlayer(Cell[][] tableau) {
        boolean isValid = false;

        while (!isValid) {
            System.out.println("choose the row [0-" + (tableau.length - 1) + "]");
            String first = sc.nextLine().trim();
            System.out.println("choose the column [0-" + (tableau[0].length - 1) + "]");
            String second = sc.nextLine().trim();
            System.out.println("you choose  " + first + " and " + second);


            if (Integer.parseInt(first) >= 0 && Integer.parseInt(first) <= 2) {
                if (Integer.parseInt(second) >= 0 && Integer.parseInt(second) <= 2) {
                    if (!isUsed(Integer.parseInt(first), Integer.parseInt(second),tableau)) {
                        return new int[]{Integer.parseInt(first), Integer.parseInt(second)};
                    } else {
                        System.out.println("retry");
                    }
                } else {
                    System.out.println("second value is not an integer");
                }
            } else {
                System.out.println("first value is not an integer");
            }
        }
        System.out.println("out of the while");
        return null;
    }

    public int[] getMoveFromArtificial(Cell [][] tableau) {
        int row, col;
        do {
            row = randomNumbers.nextInt(tableau.length);
            col = randomNumbers.nextInt(tableau[0].length);
        } while (isUsed(row, col,tableau));

        System.out.println("IA chooses " + row + " and " + col);
        return new int[]{row, col};
    }
}
