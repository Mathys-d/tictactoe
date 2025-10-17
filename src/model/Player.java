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



    private State state = State.WAITING;
    public enum State {
        WAITING, PLAYING, WON, LOST
    }
    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }

    public String getRepresentation() {
        return representation;
    }
    public String setCrossSymbol() {
        return representation = cross;
    }
    public String setCirclesSymbol() {
        return representation = circle;
    }

    public String setRepresentation() {
        boolean a = false;
        while (a == false) {
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