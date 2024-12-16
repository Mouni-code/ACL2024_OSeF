package model;

public class Element {
    public int health;
    public int damage;
    public int lives;
    public static int WIDTH;
    public static int HEIGHT;
    public Position position;
     public int x;
     public int y;
      int startX;
      int startY;
    char direction = 'U'; // U: Up, D: Down, L: Left, R: Right
    int velocityX = 0;
     int velocityY = 0;
     public boolean ramasse = false;
     int inventaireTresor = 1;
     boolean delivered = false;


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
        this.velocityX = 0;
        this.velocityY =0;
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
    

    public int getinventaire(){
        return inventaireTresor ;
    }

    public void setinventaireTresor( int val ){
        inventaireTresor = val ;
    }

   
}
