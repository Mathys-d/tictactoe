package model;
//
public class Board {

    public int[] getBoard(int gameNb) {
        return switch (gameNb) {
            // X - Y - TYPE OF GAME - WinLen
            case 1 -> {
                System.out.println("you choose TicTacToe");
                yield new int[]{3, 3, 1, 3};
            }
            case 2 -> {
                System.out.println("you choose Gomoku");
                yield new int[]{15, 15, 2, 5};
            }
            case 3 -> {
                System.out.println("you choose Power 4");
                yield new int[]{6, 7, 3, 4};
            }
            default -> new int[0];
        };
    }
}
