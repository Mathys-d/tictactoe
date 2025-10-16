package view;

import controller.GameController;
import model.Cell;
import model.TicTacToe;

import java.util.Scanner;

public class Menu {

    InteractionUtilisateur interfaceMenu;
    GameController gameController;

    Scanner sc = new Scanner(System.in);

    public Menu() {}

    public int[] chooseGame() {
        boolean a = false;
        int[] choiceListe = new int[3];

        while (!a) {
            System.out.println("choose your game (TicTacToe (1))");
            String choice = sc.nextLine();

            int option;
            try {
                option = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("wrong input, please enter 1");
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
                default:
                    System.out.println("you choose TicTacToe");
                    choiceListe[0] = 3;
                    choiceListe[1] = 3;
                    choiceListe[2] = 1;
                    a = true;
                    return choiceListe;}
        }
        return choiceListe;
    }

    public void start() {
        int[] gameChoice = chooseGame();

        switch (gameChoice[2]) {
            case 1:
                Cell[][] tableau = new Cell[gameChoice[0]][gameChoice[1]];
                InteractionUtilisateur interfaceMenu = new InteractionUtilisateur(tableau, gameChoice[2]);
                GameController gameController = new GameController(interfaceMenu, gameChoice[2], gameChoice[0], gameChoice[1]);
                gameController.start(gameChoice);

                break;
            default:
                System.out.println("Invalid game choice");
        }
    }
}