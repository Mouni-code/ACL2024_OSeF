package model;

public class Element {
    public int health;
    public int damage;
    public int lives;
    public static int WIDTH;
    public static int HEIGHT;
    public Position position;
     int x;
     int y;
      int startX;
      int startY;
    char direction = 'U'; // U: Up, D: Down, L: Left, R: Right
    int velocityX = 0;
     int velocityY = 0;

    public Scene scene;

    public Element(int x, int y, int characterWIDTH, int characterHEIGHT) {
        this.position = new Position(startX, startY);
        this.x = x;
        this.y = y;
        WIDTH = characterWIDTH;
        HEIGHT = characterHEIGHT;
        this.startX = x;
        this.startY = y;
    }


    public void reset() {
        this.x = this.startX;
        this.y = this.startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getDirection() {
        return direction;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
