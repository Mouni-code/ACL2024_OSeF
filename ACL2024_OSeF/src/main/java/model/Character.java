package model;
import java.util.ArrayList;
import java.util.List;


public abstract class Character {
    protected Position position;
    public int health;
	public int damage;
    public int lives;
    public int characterWIDTH;
    public int characterHEIGHT;

    public Scene scene;
    public Character(int startX, int startY, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage) {
        this.position = new Position(startX, startY);
        this.lives = lives;
        this.characterHEIGHT= characterHEIGHT;
        this.characterWIDTH =characterWIDTH;
        this.health =health;
        this.damage=damage;
    }

    public List<Integer> getPosition() { 
        int x = position.getX();
        int y = position.getY();
        List<Integer> positionList = new ArrayList<>();
        positionList.add(x);
        positionList.add(y);
        return positionList;
    }
    public void setPosition(int x, int y) {
        this.position.setX(x); // Met à jour la position X
        this.position.setY(y); // Met à jour la position Y
    }

    public void setLives(int vies){
        this.lives = vies;
    }

    public void move(String direction) {
        final LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF/src/main/java/model/Laby"));
        char[][] maze = labyDess.getMaze();
    
        if (lives <= 0) {
            return; // Le héros ne peut plus bouger s'il n'a plus de vies
        }
    
        // Dimensions de la carte et du héros
        final int mapWidth = scene.getWidth();
        final int mapHeight = scene.getHeight();
        final int cellSize = 40;
        final int moveStep = 10; // Pas de déplacement
        boolean collision = false;
    
        int characterX = this.getPosition().get(0);
        int characterY = this.getPosition().get(1);
    
        // Sauvegarde de la position initiale du héros
        int posX = characterX;
        int posY = characterY;
    
        // Calcul des nouvelles coordonnées selon la direction
        switch (direction.toUpperCase()) {
            case "UP":
                if (characterY > 0) { // Limites hautes
                    for (int x = characterX; x < characterX + characterWIDTH; x += cellSize) {
                        int caseXToCheck = x / cellSize;
                        int caseYToCheck = (characterY - moveStep) / cellSize; // Position future (haut)
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) characterY -= moveStep; // Déplacement effectif
                }
                break;
    
            case "DOWN":
                if (characterY + characterHEIGHT < mapHeight) { // Limites basses
                    for (int x = characterX; x < characterX + characterWIDTH; x += cellSize) {
                        int caseXToCheck = x / cellSize;
                        int caseYToCheck = (characterY + characterHEIGHT + moveStep) / cellSize; // Position future (bas)
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) characterY += moveStep; // Déplacement effectif
                }
                break;
    
            case "LEFT":
                if (characterX > 0) { // Limites gauche
                    for (int y = characterY; y < characterY + characterHEIGHT; y += cellSize) {
                        int caseXToCheck = (characterX - moveStep) / cellSize;
                        int caseYToCheck = y / cellSize;
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) characterX -= moveStep; // Déplacement effectif
                }
                break;
    
            case "RIGHT":
                if (characterX + characterWIDTH < mapWidth) { // Limites droite
                    for (int y = characterY; y < characterY + characterHEIGHT; y += cellSize) {
                        int caseXToCheck = (characterX + characterWIDTH + moveStep) / cellSize;
                        int caseYToCheck = y / cellSize;
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) characterX += moveStep; // Déplacement effectif
                }
                break;
    
            default:
                System.out.println("Direction non valide !");
                break;
        }
    
        // Gestion de la collision
        if (collision) {
            characterX = posX; // Annuler le déplacement (X)
            characterY = posY; // Annuler le déplacement (Y)
            System.out.println("Collision détectée !");
        }
    
        // Affichage de la position actuelle
        System.out.println("Position actuelle du héros : (" + characterX + ", " + characterY + ")");
    }
    
}

