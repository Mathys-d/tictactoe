package Game;

import Board.Cell;
import User.ArtificialPlayer;
import User.Player;
import User.Opponent;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    protected int size;
    protected int x;
    protected int y;
    Cell[][] tableau;
    Player player = new Player();
    Opponent enemy = new Opponent();
    InteractionUtilisateur interfaceMenu =  new InteractionUtilisateur();
    ArtificialPlayer ia1 = new ArtificialPlayer();
    ArtificialPlayer ia2 = new ArtificialPlayer();
    Scanner sc = new Scanner(System.in);
    int cpt = 0;
    Random randomNumbers = new Random();

    public TicTacToe() {
        this.size = 3;
        this.x = size;
        this.y = size;
        this.tableau = new Cell[x][y];
    }

    public void play() {
        init();
        int menuChoice = startMenu();
        //solo
        if (menuChoice == 1) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                ia1.setCirclesSymbol();
                game(menuChoice);
            } else {
                ia1.setCrossSymbol();
                game(menuChoice);
            }
        }
        //multipalyer
        else if (menuChoice == 2) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                enemy.setCirclesSymbol();
                game(menuChoice);
            } else {
                enemy.setCrossSymbol();
                game(menuChoice);
            }
        }else if (menuChoice == 3) {
            ia1.setCrossSymbol();
            ia2.setCirclesSymbol();
                game(menuChoice);
        }//wrong choice
        else if (menuChoice == 0) {
            startMenu();
        }
    }

    public void game(int menuChoice) {
        //solo
        if (menuChoice == 1) {
            System.out.println("Solo mode");
            while (!isFull(tableau) && !winCondition()) {
                usher(menuChoice);
                display();
                isOwnedBy();
            }
        }//multiplayer
        else if (menuChoice == 2) {
            System.out.println("multiplayer mode");
            while (!isFull(tableau) && !winCondition()) {
                usher(menuChoice);
                display();
                isOwnedBy();
            }
        }
        if (menuChoice == 3) {
            System.out.println("ia vs ia mode");
            while (!isFull(tableau) && !winCondition()) {
                if (cpt % 2 == 0 || cpt == 0) {
                    usher(3);
                    display();
                    isOwnedBy();
                }else {
                    usher(menuChoice);
                    display();
                    isOwnedBy();
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println("-------------------");
            System.out.print("| ");

            for (int j = 0; j < size; j++) {
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

    protected void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tableau[i][j] == null) {
                    tableau[i][j] = new Cell();
                }
            }
        }
    }

    public void usher(int menuChoice) {
        int[] move;
        String symbol;

        switch (menuChoice) {
            case 1: // Solo
                if (cpt % 2 == 0) { // Tour du joueur
                    System.out.println("Player is playing");
                    move = getMoveFromPlayer();
                    symbol = player.getRepresentation();
                } else { // Tour de l'IA
                    System.out.println("AI is playing");
                    move = getMoveFromArtificial();
                    symbol = ia1.getRepresentation();
                }
                break;

            case 2: // Multiplayer
                if (cpt % 2 == 0) { // Joueur 1
                    System.out.println("Player 1 is playing");
                    move = getMoveFromPlayer();
                    symbol = player.getRepresentation();
                } else { // Joueur 2 / Enemy
                    System.out.println("Player 2 is playing");
                    move = getMoveFromPlayer(); // demander au joueur 2
                    symbol = enemy.getRepresentation();
                }
                break;

            case 3: // IA vs IA
                if (cpt % 2 == 0) { // IA1
                    System.out.println("AI 1 is playing");
                    move = getMoveFromArtificial();
                    symbol = ia1.getRepresentation();
                } else { // IA2
                    System.out.println("AI 2 is playing");
                    move = getMoveFromArtificial();
                    symbol = ia2.getRepresentation();
                }
                break;

            default:
                return; // Sécurité
        }

        // Placer le symbole sur le tableau
        int row = move[0];
        int col = move[1];
        tableau[row][col].setRepresentation(symbol);

        // Tour suivant
        cpt++;
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

    public boolean isUsed(int i, int j) {
        if (!tableau[i][j].isEmpty()) {
            return true;
        }
        return false;
    }

    public String isOwnedBy() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String symbol = tableau[i][j].getSymbol();
                return symbol;
            }
        }
        return "empty";
    }

    public boolean winCondition() {
        //<---> horizontal et vertical search
        for (int i = 0; i < size; i++) {
            if (isRowWin(i) || isColWin(i)) {
                System.out.println("you win");
                return true;
            }
        }

        // diagonale principale (L-UP TO R-DOWN)
        boolean mainDiag = true;
        String firstSymbol = tableau[0][0].getSymbol();
        if (firstSymbol != null && !firstSymbol.trim().isEmpty() && !firstSymbol.equals(" ")) {
            for (int i = 1; i < size; i++) {
                String symbol = tableau[i][i].getSymbol();
                if (symbol == null || symbol.trim().isEmpty() || !symbol.equals(firstSymbol)) {
                    mainDiag = false;
                    break;
                }
            }
            if (mainDiag) {
                System.out.println("you win");
                return true;
            }
        }

        // diagonale secondaire (L-DOWN TO R-UP)
        boolean antiDiag = true;
        firstSymbol = tableau[size - 1][0].getSymbol();
        if (firstSymbol != null && !firstSymbol.trim().isEmpty() && !firstSymbol.equals(" ")) {
            for (int i = 1; i < size; i++) {
                String symbol = tableau[size - 1 - i][i].getSymbol();
                if (symbol == null || symbol.trim().isEmpty() || !symbol.equals(firstSymbol)) {
                    antiDiag = false;
                    break;
                }
            }
            if (antiDiag) {
                System.out.println("you win");
                return true;
            }
        }

        //draw
        if (isFull(tableau)) {
            System.out.println("draw");
            return true;
        }

        return false;
    }

    public boolean isRowWin(int row) {
        int crossStreak = 0;
        int circleStreak = 0;

        for (int i = 0; i < size; i++) {
            String symbol = tableau[row][i].getSymbol();

            if (symbol.equals("X")) {
                crossStreak++;
                circleStreak = 0;
            } else if (symbol.equals("O")) {
                circleStreak++;
                crossStreak = 0;
            } else {
                crossStreak = 0;
                circleStreak = 0;
            }

            if (crossStreak == size || circleStreak == size) {
                return true;
            }
        }
        return false;
    }

    public boolean isColWin(int col) {
        int crossStreak = 0;
        int circleStreak = 0;

        for (int i = 0; i < size; i++) {
            String symbol = tableau[i][col].getSymbol();

            if (symbol.equals("X")) {
                crossStreak++;
                circleStreak = 0;
            } else if (symbol.equals("O")) {
                circleStreak++;
                crossStreak = 0;
            } else {
                crossStreak = 0;
                circleStreak = 0;
            }

            if (crossStreak == size || circleStreak == size) {
                return true;
            }
        }
        return false;
    }

    public boolean testCheck(String symbol) {
        int crossStreak = 0;
        int circleStreak = 0;

        if (symbol.equals("X")) {
            crossStreak++;
            circleStreak = 0;
        } else if (symbol.equals("O")) {
            circleStreak++;
            crossStreak = 0;
        } else {
            crossStreak = 0;
            circleStreak = 0;
        }

        if (crossStreak == size || circleStreak == size) {
            System.out.println("you win");
            return true;
        }
        return false;
    }

    public int[] getMoveFromPlayer() {
        boolean isValid = false;

        while (!isValid) {
            System.out.println("choose the row [0-2]");
            String first = sc.nextLine().trim();
            System.out.println("choose the column [0-2]");
            String second = sc.nextLine().trim();
            System.out.println("you choose  " + first + " and " + second);

            if (Integer.parseInt(first) >= 0 && Integer.parseInt(first) <= 2) {
                if (Integer.parseInt(second) >= 0 && Integer.parseInt(second) <= 2) {
                    if (!isUsed(Integer.parseInt(first), Integer.parseInt(second))) {
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

    public int[] getMoveFromArtificial() {
        int row, col;
        do {
            row = randomNumbers.nextInt(3);
            col = randomNumbers.nextInt(3);
        } while (isUsed(row, col));

        System.out.println("IA chooses " + row + " and " + col);
        return new int[]{row, col};
    }

    public String getRandomNumber() {
        int nb = randomNumbers.nextInt(3);
        return String.valueOf(nb);
    }

    public String setGameSymbole() {
        return this.player.setRepresentation();
    }
}