package model;

public class Trap extends Case {
    private boolean isTrap;

    public Trap(Position position) {
        super(position);
        this.isTrap = true;
    }

    public boolean isTrap() {
        return isTrap;
    }
}
