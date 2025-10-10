package game;

public class Menu {
    InteractionUtilisateur interfaceMenu;
    TicTacToe ticTacToe;
    Gomoku gomoku;

    public Menu() {
        this.interfaceMenu = new InteractionUtilisateur();
    }

    public void start(){
        int [] gameChoice = interfaceMenu.chooseGame();

        switch (gameChoice[2]) {
            case 1:
                this.ticTacToe = new TicTacToe();
                ticTacToe.start(gameChoice);
            case 2:
                this.gomoku = new Gomoku();
                gomoku.start(gameChoice);
        }
    }
}
