package model;

public class Magic extends Case {
    private boolean isMagic;

    public Magic(Position position) {
        super(position);
        this.isMagic = true;
    }

    public boolean isMagic() {
        return isMagic;
    }
}
