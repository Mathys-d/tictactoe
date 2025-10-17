package view;

import controller.EnumController;
import controller.MyGameController;
import model.Cell;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    EnumController enumController = new EnumController();

    public Menu() {}

    public int[] chooseGame() {
        boolean a = false;

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
            a = true;
            return enumController.getBoard(option);
        }
        return null;
    }

    public void start() {
        int[] gameChoice = chooseGame();
        if (gameChoice[2]==1) {
            Cell[][] tableau = new Cell[gameChoice[0]][gameChoice[1]];
            InteractionUtilisateur interfaceMenu = new InteractionUtilisateur(tableau, gameChoice[2]);
            MyGameController gameController = new MyGameController(interfaceMenu, gameChoice[2], gameChoice[0], gameChoice[1]);
            gameController.start(gameChoice);
        }else{
                System.out.println("Invalid game choice");
        }
    }
}