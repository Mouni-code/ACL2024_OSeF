package model;

public class Hero extends Character {
    private int vitesse ;

    public Hero(int startX, int startY, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage, int v) {
        super( startX,  startY,  characterWIDTH,  characterHEIGHT,  lives,  health,  damage);
        vitesse = v ;
    }

    public void moveHero(String direction) {
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

    public int getVit(){
        return vitesse ;
    }

    public void setVit( int v ){
        vitesse = v ;
    }
}
