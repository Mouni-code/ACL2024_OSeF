package model;

public class Monster extends Element {
    private boolean isIntelligent;
    private int speed;

    public Monster(int startX, int startY, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage, int speed, boolean isIntelligent) {
        super( startX,  startY,  WIDTH,  HEIGHT);
        this.position = new Position(startX, startY);
        this.health = 100;
        this.damage = 15;
        this.speed = 1;
        this.isIntelligent = isIntelligent;
    }

    
    // public void moveMonster(String direction) {
    //     if (!isIntelligent) {
          
    //         String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
    //         direction = directions[new Random().nextInt(directions.length)];
    //     }
        
    //     switch (direction.trim().toUpperCase()) {
    //         case "UP":
    //             position.setY(position.getY() + speed);
    //             break;
    //         case "DOWN":
    //             position.setY(position.getY() - speed);
    //             break;
    //         case "LEFT":
    //             position.setX(position.getX() - speed);
    //             break;
    //         case "RIGHT":
    //             position.setX(position.getX() + speed);
    //             break;
    //     }
    // }

    // public void poursuivreHero(Position heroPosition) {
    //     if (isIntelligent) {
          
    //         if (Math.abs(position.getX() - heroPosition.getX()) > Math.abs(position.getY() - heroPosition.getY())) {
    //             if (position.getX() < heroPosition.getX()) {
    //                 move("RIGHT");
    //             } else {
    //                 move("LEFT");
    //             }
    //         } else {
    //             if (position.getY() < heroPosition.getY()) {
    //                 move("UP");
    //             } else {
    //                 move("DOWN");
    //             }
    //         }
    //     }
    // }
}