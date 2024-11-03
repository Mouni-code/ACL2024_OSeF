package model;

public class Passage extends Case {
    private boolean isPassage;

    public Passage(Position position) {
        super(position);
        this.isPassage = true;
    }

    public boolean isPassage() {
        return isPassage;
    }
}
