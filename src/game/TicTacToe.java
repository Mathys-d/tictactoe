package game;

import board.Cell;
import user.ArtificialPlayer;
import user.Player;
import user.Opponent;


public class TicTacToe {
    protected int sizeX;
    protected int sizeY;
    Cell[][] tableau;
    Player player = new Player();
    Opponent enemy = new Opponent();
    InteractionUtilisateur interfaceMenu = new InteractionUtilisateur();
    GameLogic gameLogic = new GameLogic(interfaceMenu);
    ArtificialPlayer ia1 = new ArtificialPlayer();
    ArtificialPlayer ia2 = new ArtificialPlayer();
    int cpt = 0;

    public TicTacToe() { }


    public void start() {
        int[] gameChoice = interfaceMenu.chooseGame();
        this.sizeX = gameChoice[0];
        this.sizeY = gameChoice[1];
        this.tableau = new Cell[sizeX][sizeY];

        if (gameChoice[2] == 1) {
            launchGame(enemy, ia1, ia2, sizeX, sizeY, gameChoice[2]);
        } else if (gameChoice[2] == 2) {
            launchGame(enemy, ia1, ia2, sizeX, sizeY, gameChoice[2]);
        } else if (gameChoice[2] == 3) {
            launchGame(enemy, ia1, ia2, sizeX, sizeY, gameChoice[2]);
        }
    }

    public void launchGame(Opponent enemy, ArtificialPlayer ia1, ArtificialPlayer ia2, int sizeX, int sizeY, int whichGame) {
        System.out.println("LG");
        init();
        int menuChoice = interfaceMenu.startMenu();
        //solo

        if (menuChoice == 1) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                ia1.setCirclesSymbol();
                game(menuChoice, whichGame);
            } else {
                ia1.setCrossSymbol();
                game(menuChoice, whichGame);
            }
        }
        //multipalyer
        else if (menuChoice == 2) {
            String symbolChoice = setGameSymbole();
            if (symbolChoice.equalsIgnoreCase("X")) {
                enemy.setCirclesSymbol();
                game(menuChoice, whichGame);
            } else {
                enemy.setCrossSymbol();
                game(menuChoice, whichGame);
            }
        } else if (menuChoice == 3) {
            ia1.setCrossSymbol();
            ia2.setCirclesSymbol();
            game(menuChoice, whichGame);
        }//wrong choice
        else if (menuChoice == 0) {
            interfaceMenu.startMenu();
        }
    }

    public void game(int menuChoice, int whichGame) {
        switch (menuChoice) {
            case 1:
                System.out.println("Solo mode");
                break;
            case 2:
                System.out.println("Multiplayer mode");
                break;
            case 3:
                System.out.println("IA vs IA mode");
                break;
            default:
                System.out.println("Invalid mode");
                return;
        }

        // Boucle principale commune à tous les modes
        while (!gameLogic.isFull(tableau) && !gameLogic.winCondition(sizeX, sizeY, tableau, whichGame)) {
            gameLogic.usher(menuChoice, cpt, player, enemy, ia1, ia2, tableau);
            interfaceMenu.display(sizeX, sizeY, tableau);

            // Cette ligne ne faisait effet que dans le mode IA vs IA
            if (menuChoice == 3) {
                gameLogic.isOwnedBy(sizeX, sizeY, tableau);
            }

            cpt++;
        }
    }

    protected void init() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (tableau[i][j] == null) {
                    tableau[i][j] = new Cell();
                }
            }
        }
    }

    public String setGameSymbole() {
        return this.player.setRepresentation();
    }
}