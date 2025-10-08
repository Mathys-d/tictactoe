package User;

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


        public String setRepresentation() {
        boolean a = false;
        while(a == false) {
            System.out.println("choose your symbol : X | O");
            String symbol = sc.nextLine();
            if (symbol.equalsIgnoreCase("x")) {
                System.out.println("you choose " + cross);
                a = true;
                return representation = cross;
            } else if (symbol.equalsIgnoreCase("O")) {
                System.out.println("you choose " + circle);
                a = true;
                return representation = circle;
            } else {
                System.out.println("wrong input  " );
            }
        }
        return "wroooonnnngggg";
    }

}