package view;

import controller.MyGameController;
import model.Board;
import model.Cell;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Board board = new Board();

    public void start() {
        int[] gameChoice = chooseGame();
        if (gameChoice[2]>=1 && gameChoice[2]<=3) {
            Cell[][] tableau = new Cell[gameChoice[0]][gameChoice[1]];// create the tab wight the X - Y len
            InteractionUtilisateur interfaceMenu = new InteractionUtilisateur(tableau, gameChoice[2]);
            MyGameController gameController = new MyGameController(interfaceMenu, gameChoice[0], gameChoice[1], gameChoice[2]);
            gameController.start(gameChoice);
        }else{
            System.out.println("Invalid game choice");
        }
    }

    public int[] chooseGame() {
        boolean a = false;

        while (!a) {
            System.out.println("choose your game (TicTacToe (1)/ gomoku (2)/ power 4 (3))");
            String choice = sc.nextLine();
            int option;
            try {
                option = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("wrong input, please enter 1");
                continue;
            }
            if (option >= 1 && option <= 3) {
                a = true;
                return board.getBoard(option);
            }
        }
        return null;
    }
}