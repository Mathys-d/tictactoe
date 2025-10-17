package model;

import java.util.Scanner;

public class Player {
    public String cross;
    public String circle;
    public String representation;

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