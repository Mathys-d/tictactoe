package view;

import model.TicTacToe;

import java.util.Scanner;

public class Menu {

    InteractionUtilisateur interfaceMenu;
    TicTacToe ticTacToe;

    Scanner sc = new Scanner(System.in);

    public Menu() {
    }

    public int[] chooseGame() {
        boolean a = false;
        int[] choiceListe = new int[3];

        while (!a) {
            System.out.println("choose your game (TicTacToe (1)/ gomoku (2)/ power 4 (3))");
            String choice = sc.nextLine();

            int option;
            try {
                option = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("wrong input, please enter 1, 2, or 3");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("you choose TicTacToe");
                    choiceListe[0] = 3;
                    choiceListe[1] = 3;
                    choiceListe[2] = 1;
                    a = true;
                    return choiceListe;
                case 2:
                    System.out.println("you choose gomoku");
                    choiceListe[0] = 15;
                    choiceListe[1] = 15;
                    choiceListe[2] = 2;
                    a = true;
                    return choiceListe;
                case 3:
                    System.out.println("you choose power 4");
                    choiceListe[0] = 7;
                    choiceListe[1] = 6;
                    choiceListe[2] = 3;
                    a = true;
                    return choiceListe;
                default:
                    System.out.println("wrong input, please enter 1, 2, or 3");
            }
        }

        return choiceListe;
    }

    public void start() {
        int[] gameChoice = chooseGame();

        switch (gameChoice[2]) {
            case 1:
                this.ticTacToe = new TicTacToe(gameChoice[2]);
                ticTacToe.start(gameChoice);
                break;
            default:
                System.out.println("Invalid game choice");
        }
    }
}
