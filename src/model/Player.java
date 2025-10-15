package model;

import java.util.Scanner;

public class Player {
    protected String cross;
    protected String circle;
    protected String representation;
    Scanner sc = new Scanner(System.in);

    public Player () {
        this.cross = "X";
        this.circle = "O";
        this.representation = "";
    }

    public String getRepresentation() {
        return representation;
    }

    public String setCrossSymbol(){
        return representation = cross;
    }
    public String setCirclesSymbol(){
        return representation = circle;
    }

}