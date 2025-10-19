package model;

import java.util.Scanner;

public class Player {
    public String cross;
    public String circle;
    public String representation;
    public String Name;
    Scanner sc = new Scanner(System.in);

    public Player() {
        this.cross = "X";
        this.circle = "O";
        this.representation = "";
    }

    public String getRepresentation() {
        return representation;
    }
    public void setCrossSymbol() {
        representation = cross;
    }
    public void setCirclesSymbol() {
        representation = circle;
    }

    public String setRepresentation() {
        boolean a = false;
        while (!a) {
            System.out.println("choose your symbol : X | O");
            String symbol = sc.nextLine();
            if (symbol.equalsIgnoreCase("x")) {
                System.out.println("you choose X");
                a = true;
                return representation = cross;
            } else if (symbol.equalsIgnoreCase("O")) {
                System.out.println("you choose O");
                a = true;
                return representation = circle;
            } else {
                System.out.println("wrong input  ");
            }
        }
        return "wroooonnnngggg";
    }


}