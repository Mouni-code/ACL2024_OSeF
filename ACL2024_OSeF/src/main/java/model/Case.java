package model;

public abstract class Case  {
    protected Position position;

    public Case(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
