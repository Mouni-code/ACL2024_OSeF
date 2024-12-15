package model;
//A VOIR SI ON LAJOUTE
public class Ghost extends Element {

    int speedx;
    int speedy;

    public Ghost(int x, int y, int WIDTH, int HEIGHT, int lives, int health, int damage, int speedx , int speedy) {
        super( x,  y,  WIDTH,  HEIGHT);
        this.speedx = speedx;
        this.speedy = speedy;
        this.lives = lives;
        this.health = health;
        this.damage = damage;
    }

}