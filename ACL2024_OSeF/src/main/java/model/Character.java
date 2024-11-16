package model;

public abstract class Character {
    protected Position position;
    public int health;
	public int damage;

    public Character(int startX, int startY) {
        this.position = new Position(startX, startY);
    }

    public Position getPosition() { 
        return position;
    }

    public abstract void move(String direction);
}
