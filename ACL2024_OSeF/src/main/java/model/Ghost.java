package model;
//A VOIR SI ON LAJOUTE
public class Ghost extends Element {


    public Ghost(int x, int y, int WIDTH, int HEIGHT, int lives, int health, int damage, int speedx , int speedy) {
        super( x,  y,  WIDTH,  HEIGHT);
        this.lives = lives;
        this.health = health;
        this.damage = damage;
    }

}