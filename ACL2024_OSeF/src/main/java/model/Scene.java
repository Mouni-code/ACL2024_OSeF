package model;
//SOUCIS : YOU DIED Dès le début!
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Scene extends JPanel implements KeyListener {

    Hero hero = new Hero(100, 70, 50 , 50, 3, 100, 20, 1);
    Monster monster = new Monster(350, 200, 50, 50, 1, 100, 20, 1, false);
    Monster monster2 = new Monster(500, 300, 50, 50, 1, 100, 20, 1, true);
    Ghost ghost1 = new Ghost(400, 400, 50, 50, 1, 100, 20, 1);
    Ghost ghost2 = new Ghost(600, 100,50, 50, 1, 100, 20, 1);
    Ghost ghost3 = new Ghost(200, 500, 50, 50, 1, 100, 20, 1);

    int heroX = hero.getPosition().get(0);
    int heroY =  hero.getPosition().get(1);
    int heroWidth = hero.characterWIDTH;
    int heroHeight = hero.characterHEIGHT;
    int monsterX = monster.getPosition().get(0);
    int monsterY = monster.getPosition().get(1);
    int monsterHealth = monster.health;
    int ghost2X = ghost2.getPosition().get(0);
    int ghost2Y = ghost2.getPosition().get(1);
    int ghost2Health = ghost2.health;
    private ImageIcon icofond;
    private Image imagefond;

    private ImageIcon icohero;
    private Image imagehero;

    private ImageIcon icomonstre;
    private Image imagemonstre;

    private ImageIcon icoghost;
    private Image imageghost;

    private ImageIcon icoYouDied;
    private Image imageYouDied;
     
    int ghost1X = ghost1.getPosition().get(0);
    int ghost1Y = ghost1.getPosition().get(1);
    int ghost1Health = ghost1.health;
    int ghost3X = ghost3.getPosition().get(0);
    int ghost3Y = ghost3.getPosition().get(1);
    int ghost3Health = ghost3.health;
    int heroHealth = hero.health;
    int heroMaxHealth = 100;
    int heroLives = hero.lives;
    private int monsterMaxHealth = 100;
    private int monster2X;
    private int monster2Y;
    private int monster2Health;
    private int monster2MaxHealth = 100;
    private int ghost1MaxHealth = 100;
    private int[][] ghost1Targets = new int[5][2];
    private int ghost1TargetIndex = 0;
    private int ghost2MaxHealth = 100;
    private int[][] ghost2Targets = new int[5][2];
    private int ghost2TargetIndex = 0;
    private int ghost3MaxHealth = 100;
    private int[][] ghost3Targets = new int[5][2];
    private int ghost3TargetIndex = 0;

    private static final int MAX_DISTANCE = 300;
    private static final int MAP_WIDTH = 800;
    private static final int MAP_HEIGHT = 600;
    private static final int GHOST_STEP = 5;

    Position heroPosition = new Position(heroX, heroY);

    private boolean isCollision = false;
    private Timer collisionTimer;
    private int collisionDuration = 500;

    private Random random = new Random();

    private LabyDess labyDess= new LabyDess(new Labyrinthe("ACL2024_OSeF/src/main/java/model/Laby"));
    public  char[][] map = labyDess.maze;

    public Scene() {
        super();

        // Charger les images
        icofond = new ImageIcon(getClass().getResource("/images/fondecran.png"));
        this.imagefond = this.icofond.getImage();

        icohero = new ImageIcon(getClass().getResource("/images/heroagain.png"));
        this.imagehero = this.icohero.getImage();

        icomonstre = new ImageIcon(getClass().getResource("/images/monster.png"));
        this.imagemonstre = this.icomonstre.getImage();

        icoghost = new ImageIcon(getClass().getResource("/images/ghost1.png"));
        this.imageghost = this.icoghost.getImage();

        icoYouDied = new ImageIcon(getClass().getResource("/images/youdied.png"));
        this.imageYouDied = this.icoYouDied.getImage();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);

        //public Character(int startX, int startY, int characterWIDTH, int characterHEIGHT, int lives, int health, int damage)

        generateRandomPoints(ghost1Targets);
        generateRandomPoints(ghost2Targets);
        generateRandomPoints(ghost3Targets);

        Timer moveTimer = new Timer(100, e -> {
            suivreHero(monsterX, monsterY, false);
            suivreHero(monster2X, monster2Y, true);

            moveGhostToNextPoint(ghost1X, ghost1Y, ghost1Targets, ghost1TargetIndex, true, 1);
            moveGhostToNextPoint(ghost2X, ghost2Y, ghost2Targets, ghost2TargetIndex, true, 2);
            moveGhostToNextPoint(ghost3X, ghost3Y, ghost3Targets, ghost3TargetIndex, true, 3);

            // Attaquer uniquement si la santé du monstre/ghost est > 0
            if (monsterHealth > 0) attackHero(monsterX, monsterY);
            if (monster2Health > 0) attackHero(monster2X, monster2Y);
            if (ghost1Health > 0) attackHero(ghost1X, ghost1Y);
            if (ghost2Health > 0) attackHero(ghost2X, ghost2Y);
            if (ghost3Health > 0) attackHero(ghost3X, ghost3Y);

            repaint();
        });
        moveTimer.start();
    
    }

    public void init(){
        System.out.println("Initialisation :");
        System.out.println("Héros : (" + heroX + ", " + heroY + ")");
        System.out.println("Monstre : (" + monsterX + ", " + monsterY + ")");
        System.out.println("Ghost1 : (" + ghost1X + ", " + ghost1Y + ")");
        System.out.println("Ghost2 : (" + ghost2X + ", " + ghost2Y + ")");
        System.out.println("Ghost3 : (" + ghost3X + ", " + ghost3Y + ")");
        System.out.println("nombre de vie:"+ heroLives);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(this.imagefond, 0, 0, null);
        drawLaby(g2);

        if (heroLives <= 0) {
            int imageWidth = imageYouDied.getWidth(null);
            int imageHeight = imageYouDied.getHeight(null);

            int centerX = (this.getWidth() - imageWidth) / 2;
            int centerY = (this.getHeight() - imageHeight) / 2;

            g2.drawImage(this.imageYouDied, centerX, centerY, null);
            return;
        }

        g2.drawImage(this.imagehero, heroX, heroY, null);

        if (monsterHealth > 0) {
            g2.drawImage(this.imagemonstre, monsterX, monsterY, null);
            drawMonsterHealthBar(g2, monsterX, monsterY, monsterHealth, monsterMaxHealth);
        }
        if (monster2Health > 0) {
            g2.drawImage(this.imagemonstre, monster2X, monster2Y, null);
            drawMonsterHealthBar(g2, monster2X, monster2Y, monster2Health, monster2MaxHealth);
        }
        if (ghost1Health > 0) {
            g2.drawImage(this.imageghost, ghost1X, ghost1Y, null);
            drawMonsterHealthBar(g2, ghost1X, ghost1Y, ghost1Health, ghost1MaxHealth);
        }
        if (ghost2Health > 0) {
            g2.drawImage(this.imageghost, ghost2X, ghost2Y, null);
            drawMonsterHealthBar(g2, ghost2X, ghost2Y, ghost2Health, ghost2MaxHealth);
        }
        if (ghost3Health > 0) {
            g2.drawImage(this.imageghost, ghost3X, ghost3Y, null);
            drawMonsterHealthBar(g2, ghost3X, ghost3Y, ghost3Health, ghost3MaxHealth);
        }

        drawHealthBar(g2);
        drawLives(g2);
    }

    private void drawHealthBar(Graphics2D g2) {
        int barX = 10, barY = 10, barWidth = 200, barHeight = 20;
        int filledWidth = (int) ((double) heroHealth / heroMaxHealth * barWidth);

        g2.setColor(Color.GRAY);
        g2.fillRect(barX, barY, barWidth, barHeight);

        g2.setColor(Color.RED);
        g2.fillRect(barX, barY, filledWidth, barHeight);

        g2.setColor(Color.BLACK);
        g2.drawRect(barX, barY, barWidth, barHeight);
    }

    private void drawMonsterHealthBar(Graphics2D g2, int x, int y, int health, int maxHealth) {
        int barX = x, barY = y - 20, barWidth = 100, barHeight = 10;
        int filledWidth = (int) ((double) health / maxHealth * barWidth);

        g2.setColor(Color.GRAY);
        g2.fillRect(barX, barY, barWidth, barHeight);

        g2.setColor(Color.RED);
        g2.fillRect(barX, barY, filledWidth, barHeight);

        g2.setColor(Color.BLACK);
        g2.drawRect(barX, barY, barWidth, barHeight);
    }

    private void drawLives(Graphics2D g2) {
        int lifeX = 10, lifeY = 40, lifeSize = 20;

        g2.setColor(Color.RED);
        for (int i = 0; i < heroLives; i++) {
            g2.fillOval(lifeX + (i * (lifeSize + 5)), lifeY, lifeSize, lifeSize);
        }
    }

    private void drawLaby(Graphics2D g2) {
        labyDess.setNiveau(0);
        labyDess.paintComponent(g2);
    }

    private void attackHero(int enemyX, int enemyY) {
        if (enemyX == monsterX && monsterHealth <= 0) return;
        if (enemyX == monster2X && monster2Health <= 0) return;
        if (enemyX == ghost1X && ghost1Health <= 0) return;
        if (enemyX == ghost2X && ghost2Health <= 0) return;
        if (enemyX == ghost3X && ghost3Health <= 0) return;
    
        int distanceX = Math.abs(enemyX - heroX);
        int distanceY = Math.abs(enemyY - heroY);
    
        if (distanceX < 50 && distanceY < 50) {
            if (!isCollision) {
                pushHeroBack(enemyX, enemyY); // Appeler la méthode pour reculer
                startCollision();
            }
        }
    }
    

    private void pushHeroBack(int attackerX, int attackerY) {
        int pushDistance = 20;
    
        // Calcul de la position temporaire du héros après le recul
        int newHeroX = heroX;
        int newHeroY = heroY;
    
        if (attackerX < heroX) newHeroX += pushDistance;
        else if (attackerX > heroX) newHeroX -= pushDistance;
    
        if (attackerY < heroY) newHeroY += pushDistance;
        else if (attackerY > heroY) newHeroY -= pushDistance;
    
        // Correction des positions pour éviter de sortir des bordures
        heroX = Math.max(0, Math.min(newHeroX, MAP_WIDTH - heroWidth));
        heroY = Math.max(0, Math.min(newHeroY, MAP_HEIGHT - heroHeight));
    }
    
   // Méthode pour déplacer le monstre toutes les secondes (ou un autre intervalle)
    // Method for moving the monsters

    private void startCollision() {
        isCollision = true;
        heroHealth = Math.max(0, heroHealth - 10);
        if (heroHealth == 0) {
            heroLives--;
            if (heroLives > 0) heroHealth = heroMaxHealth;
        }

        if (collisionTimer != null && collisionTimer.isRunning()) {
            collisionTimer.stop();
        }
        collisionTimer = new Timer(collisionDuration, e -> isCollision = false);
        collisionTimer.setRepeats(false);
        collisionTimer.start();
    }

    private void generateRandomPoints(int[][] targets) {
        for (int i = 0; i < targets.length; i++) {
            targets[i][0] = random.nextInt(MAP_WIDTH - 50);
            targets[i][1] = random.nextInt(MAP_HEIGHT - 50);
        }
    }

    private void moveGhostToNextPoint(int ghostX, int ghostY, int[][] targets, int targetIndex, boolean isGhost, int ghostNumber) {
        int targetX = targets[targetIndex][0];
        int targetY = targets[targetIndex][1];

        if (ghostX < targetX) ghostX += GHOST_STEP;
        if (ghostX > targetX) ghostX -= GHOST_STEP;
        if (ghostY < targetY) ghostY += GHOST_STEP;
        if (ghostY > targetY) ghostY -= GHOST_STEP;

        if (Math.abs(ghostX - targetX) < GHOST_STEP && Math.abs(ghostY - targetY) < GHOST_STEP) {
            targetIndex = (targetIndex + 1) % targets.length;
        }

        if (isGhost) {
            if (ghostNumber == 1) {
                ghost1X = ghostX;
                ghost1Y = ghostY;
                ghost1TargetIndex = targetIndex;
            } else if (ghostNumber == 2) {
                ghost2X = ghostX;
                ghost2Y = ghostY;
                ghost2TargetIndex = targetIndex;
            } else if (ghostNumber == 3) {
                ghost3X = ghostX;
                ghost3Y = ghostY;
                ghost3TargetIndex = targetIndex;
            }
        }
    }

    

    private void suivreHero(int currentMonsterX, int currentMonsterY, boolean isSecondMonster) {
        int distanceX = Math.abs(currentMonsterX - heroX);
        int distanceY = Math.abs(currentMonsterY - heroY);

        if (distanceX > MAX_DISTANCE || distanceY > MAX_DISTANCE) {
            return;
        }

        if (currentMonsterX < heroX) currentMonsterX += 5;
        else currentMonsterX -= 5;

        if (currentMonsterY < heroY) currentMonsterY += 5;
        else currentMonsterY -= 5;

        if (isSecondMonster) {
            monster2X = currentMonsterX;
            monster2Y = currentMonsterY;
        } else {
            monsterX = currentMonsterX;
            monsterY = currentMonsterY;
        }
    }


    

    public void moveHero(String direction) {
        // Chargement du labyrinthe
        int lvl = labyDess.getNiveau();
        char[][] maze = labyDess.getMaze();
    
        if (heroLives <= 0) {
            return; // Le héros ne peut plus bouger s'il n'a plus de vies
        }
    
        // Dimensions de la carte et du héros
        final int mapWidth = this.getWidth();
        final int mapHeight = this.getHeight();
        final int cellSize = 40;
        final int moveStep = 10; // Pas de déplacement
        boolean collision = false;
    
        // Sauvegarde de la position initiale du héros
        int posX = heroX;
        int posY = heroY;
    
        // Calcul des nouvelles coordonnées selon la direction
        switch (direction.toUpperCase()) {
            case "UP":
                if (heroY > 0) { // Limites hautes
                    for (int x = heroX; x < heroX + heroWidth; x += cellSize) {
                        int caseXToCheck = x / cellSize;
                        int caseYToCheck = (heroY + 4 * moveStep) / cellSize; // Position future (haut)
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) heroY -= moveStep; // Déplacement effectif
                }
                break;
    
            case "DOWN":
                if (heroY + heroHeight < mapHeight) { // Limites basses
                    for (int x = heroX; x < heroX + heroWidth; x += cellSize) {
                        int caseXToCheck = x / cellSize;
                        int caseYToCheck = (heroY + 4 * moveStep + heroHeight) / cellSize; // Position future (bas)
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) heroY += moveStep; // Déplacement effectif
                }
                break;
    
            case "LEFT":
                if (heroX > 0) { // Limites gauche
                    for (int y = heroY; y < heroY + heroHeight; y += cellSize) {
                        int caseXToCheck = (heroX - moveStep) / cellSize;
                        int caseYToCheck = y / cellSize;
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) heroX -= moveStep; // Déplacement effectif
                }
                break;
    
            case "RIGHT":
                if (heroX + heroWidth < mapWidth) { // Limites droite
                    for (int y = heroY; y < heroY + heroHeight; y += cellSize) {
                        int caseXToCheck = (heroX + moveStep + heroWidth - 1) / cellSize;
                        int caseYToCheck = y / cellSize;
                        if (maze[caseYToCheck][caseXToCheck] == '#') {
                            collision = true;
                            break;
                        }
                    }
                    if (!collision) heroX += moveStep; // Déplacement effectif
                }
                break;
    
            default:
                System.out.println("Direction non valide !");
                break;
        }
    
        // Gestion de la collision
        if (collision) {
            heroX = posX; // Annuler le déplacement (X)
            heroY = posY; // Annuler le déplacement (Y)
            System.out.println("Collision détectée !");
        }
    
        // Affichage de la position actuelle
        System.out.println("Position actuelle du héros : (" + heroX + ", " + heroY + ")");
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                moveHero("UP");
                break;
            case KeyEvent.VK_DOWN:
                moveHero("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                moveHero("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                moveHero("RIGHT");
                break;
            case KeyEvent.VK_SPACE:
                heroAttack();
                break;
            default:
                System.out.println("Touche non prise en charge !");
                break;
        }
        repaint();
    }
    

    private void heroAttack() {
        // Réduire la santé uniquement si le monstre est vivant
        if (Math.abs(monsterX - heroX) < 50 && Math.abs(monsterY - heroY) < 50 && monsterHealth > 0) {
            monsterHealth = Math.max(0, monsterHealth - 20);
        }
        if (Math.abs(monster2X - heroX) < 50 && Math.abs(monster2Y - heroY) < 50 && monster2Health > 0) {
            monster2Health = Math.max(0, monster2Health - 20);
        }
        if (Math.abs(ghost1X - heroX) < 50 && Math.abs(ghost1Y - heroY) < 50 && ghost1Health > 0) {
            ghost1Health = Math.max(0, ghost1Health - 20);
        }
        if (Math.abs(ghost2X - heroX) < 50 && Math.abs(ghost2Y - heroY) < 50 && ghost2Health > 0) {
            ghost2Health = Math.max(0, ghost2Health - 20);
        }
        if (Math.abs(ghost3X - heroX) < 50 && Math.abs(ghost3Y - heroY) < 50 && ghost3Health > 0) {
            ghost3Health = Math.max(0, ghost3Health - 20);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

