package model;

public class Board {


    public int[] getBoard(int gameNb) {
        return switch (gameNb) {
            case 1 -> {
                System.out.println("you choose TicTacToe");
                yield new int[]{3, 3, 1};
            }
            case 2 -> {
                System.out.println("you choose Gomoku");
                yield new int[]{15, 15, 2};
            }
            case 3 -> {
                System.out.println("you choose Power 4");
                yield new int[]{6, 7, 3};
            }
            default -> new int[0];
        };
    }
}
