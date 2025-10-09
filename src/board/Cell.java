package board;

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

    @Override
    public String toString() {
        return this.getRepresentation();
    }

    public boolean isEmpty() {
        return " ".equals(this.representation);
    }

    public String getSymbol() {
        return this.getRepresentation().trim();
    }
}