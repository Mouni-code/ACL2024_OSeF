package model;
//A VOIR SI ON LAJOUTE
public class Ghost extends Character {

    int speed;

    public Ghost(int startX, int startY, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage, int speed) {
        super( startX,  startY,  characterWIDTH,  characterHEIGHT,  lives,  health,  damage);
        this.speed = 1;
    }

}