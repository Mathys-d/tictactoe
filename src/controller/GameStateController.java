package controller;

public enum GameStateController
{
        WAITING("Waiting"),
        PLAYING("Playing"),
        DRAW("Draw"),
        WON("Won"),
        LOST("Lost");

    private String name;

    GameStateController(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name = name;
    }
}
