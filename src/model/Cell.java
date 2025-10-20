package model;

public class Cell {

    protected String representation;

    public Cell() {
        this.representation = " ";
    }

    private String getRepresentation() {
        return " " + this.representation + " ";
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }



    public boolean isEmpty() {
        return " ".equals(this.representation);
    }

    public String getSymbol() {
        return this.getRepresentation().trim();
    }

    @Override
    public String toString() {
        return this.getRepresentation();
    }
}