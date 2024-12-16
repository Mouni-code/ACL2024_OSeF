package model;

public class Hero extends Element {
    public Position position;
    int inventaire;

    public Hero(int x, int y, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage, int vx, int vy, int inventaire) {
        super( x,  y,  WIDTH,  HEIGHT);
        this.position = new Position(x, y);
        this.lives = lives;
        this.health = health;
        this.damage = health;
        this.velocityX = vx;
        this.velocityX = vy;
        
        inventaire = 0;
    }

    public Hero(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    public void getPosition() {
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

    @Override
    public int getinventaire(){
        return inventaire ;
    }

    public void setinventaire( int val ){
        inventaire = val ;
    }

}
