package controller;

import board.Cell;
import game.InteractionUtilisateur;
import user.ArtificialPlayer;
import user.Opponent;
import user.Player;

/**
 * Classe abstraite définissant le comportement général d’un contrôleur de jeu.
 * Toutes les logiques de jeu spécifiques (TicTacToe, Gomoku, Puissance4, etc.)
 * doivent hériter de cette classe et implémenter les méthodes nécessaires.
 */
public abstract class GameController {

    protected final InteractionUtilisateur interfaceMenu;
    protected final int gameChoice;

    /**
     * Constructeur de base pour tout contrôleur de jeu.
     *
     * @param interfaceMenu Interface gérant les interactions utilisateur
     * @param gameChoice    Type de jeu choisi
     */
    public GameController(InteractionUtilisateur interfaceMenu, int gameChoice) {
        this.interfaceMenu = interfaceMenu;
        this.gameChoice = gameChoice;
    }

    /**
     * Démarre le jeu.
     * À implémenter par les sous-classes.
     */
    public abstract void startGame();

    /**
     * Gère le déroulement du tour de jeu selon le mode choisi (solo, multi, IA vs IA).
     */
    public abstract void usher(int menuChoice, int cpt,
                               Player player, Opponent enemy,
                               ArtificialPlayer ia1, ArtificialPlayer ia2,
                               Cell[][] tableau);

    /**
     * Vérifie si la grille est pleine (égalité).
     */
    public abstract boolean isFull(Cell[][] tab);

    /**
     * Vérifie les conditions de victoire du jeu.
     */
    public abstract boolean winCondition(int sizeX, int sizeY, Cell[][] tableau);

    /**
     * Détermine le symbole du propriétaire d'une case.
     */
    public abstract String isOwnedBy(int sizX, int sizY, Cell[][] tableau);

    /**
     * Vérifie si une ligne est gagnante.
     */
    public abstract boolean isRowWin(int row, int sizeY, Cell[][] tableau);

    /**
     * Vérifie si une colonne est gagnante.
     */
    public abstract boolean isColWin(int col, int sizeX, Cell[][] tableau);
}
