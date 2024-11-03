package model;

public class Treasure extends Case {
    private boolean isTreasure;

    public Treasure(Position position) {
        super(position);
        this.isTreasure = true;
    }

    public boolean isTreasure() {
        return isTreasure;
    }
}
