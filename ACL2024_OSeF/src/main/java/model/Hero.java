package model;

public class Hero extends Character {
    int startX;
    int startY;
    int vitesse ;
    int inventaire;

    Treasure tresor;
    Passage passage;

    public Hero(int startX, int startY, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage, int v, int inventaire) {
        super( startX,  startY,  characterWIDTH,  characterHEIGHT,  lives,  health,  damage);
        vitesse = v ;
        inventaire = 0;
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

    public int getinventaire(){
        return inventaire ;
    }

    public void setinventaire( int val ){
        inventaire = val ;
    }

    public void remplirInventaire(){
        if (startX == tresor.position.getX() && startY == tresor.position.getY()){
            inventaire++;
            System.out.println("L'inventaire est :"+ inventaire + " étoile(s)");
        }
    }
    
    public boolean pouvoirPassage(){
        if (startX == passage.position.getX() && startY == passage.position.getY() && inventaire == 2){
            System.out.println("Il peut passer");
            return true;
        }
        return false;
    }
}
