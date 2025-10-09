package Game;

import Board.Cell;
import User.ArtificialPlayer;
import User.Player;
import User.Opponent;

public class TicTacToe {
    protected int size;
    protected int x;
    protected int y;
    Cell[][] tableau;
    Player player = new Player();
    Opponent enemy = new Opponent();
    InteractionUtilisateur interfaceMenu = new InteractionUtilisateur();
    gameLogic gameLogic = new gameLogic(interfaceMenu);

    ArtificialPlayer ia1 = new ArtificialPlayer();
    ArtificialPlayer ia2 = new ArtificialPlayer();
    int cpt = 0;

    public TicTacToe() {
        this.size = 3;
        this.x = size;
        this.y = size;
        this.tableau = new Cell[x][y];
    }

    public void start() {
        launchGame(enemy, ia1,ia2,size,tableau);
    }

    public void game(int menuChoice) {
        //solo
        if (menuChoice == 1) {
            System.out.println("Solo mode");
            while (!gameLogic.isFull(tableau) && !gameLogic.winCondition(size,tableau)) {
                gameLogic.usher(menuChoice,cpt,player,enemy, ia1,ia2,tableau);
                interfaceMenu.display(size, tableau);
                gameLogic.isOwnedBy(size,tableau);
                cpt++;
            }
        }//multiplayer
        else if (menuChoice == 2) {
            System.out.println("multiplayer mode");
            while (!gameLogic.isFull(tableau) && !gameLogic.winCondition(size,tableau)) {
                gameLogic.usher(menuChoice,cpt,player,enemy, ia1,ia2,tableau);
                interfaceMenu.display(size, tableau);
                gameLogic.isOwnedBy(size,tableau);
                cpt++;
            }
        }
        if (menuChoice == 3) {
            System.out.println("ia vs ia mode");
            while (!gameLogic.isFull(tableau) && !gameLogic.winCondition(size,tableau)) {
                if (cpt % 2 == 0 || cpt == 0) {
                    gameLogic.usher(menuChoice,cpt,player,enemy, ia1,ia2,tableau);
                    interfaceMenu.display(size, tableau);
                    gameLogic.isOwnedBy(size,tableau);
                } else {
                    gameLogic.usher(menuChoice,cpt,player,enemy, ia1,ia2,tableau);
                    interfaceMenu.display(size, tableau);
                    gameLogic.isOwnedBy(size,tableau);
                }
                cpt++;
            }
        }
    }

    public void launchGame(Opponent enemy, ArtificialPlayer ia1,ArtificialPlayer ia2,int size, Cell [][] tableau) {
        gameLogic.init(size, tableau);
        int menuChoice = interfaceMenu.startMenu();
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
        } else if (menuChoice == 3) {
            ia1.setCrossSymbol();
            ia2.setCirclesSymbol();
            game(menuChoice);
        }//wrong choice
        else if (menuChoice == 0) {
            interfaceMenu.startMenu();
        }
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

    public String setGameSymbole() {
        return this.player.setRepresentation();
    }
}