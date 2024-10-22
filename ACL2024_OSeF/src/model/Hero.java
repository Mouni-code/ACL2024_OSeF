package model;

public class Hero {
    private Position position;

    public Hero(int startX, int startY) {
        this.position = new Position(startX, startY);
    }

    public Position getPosition() {
        return position;
    }

    public void move(String direction) {
        switch (direction.trim().toUpperCase()) {
            case "UP":
                position.setY(position.getY() + 1);
                break;
            case "DOWN":
                position.setY(position.getY() - 1);
                break;
            case "LEFT":
                position.setX(position.getX() - 1);
                break;
            case "RIGHT":
                position.setX(position.getX() + 1);
                break;
            default:
                System.out.println("Direction non reconnue. Utilisez 'UP', 'DOWN', 'LEFT', 'RIGHT'.");
                break;
        }
    }
}
