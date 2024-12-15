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

    Hero hero = new Hero(100, 70, 50 , 50, 3, 100, 20, 1, 0);
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
    private int monster2X = monster2.getPosition().get(0);
    private int monster2Y = monster2.getPosition().get(1);
    private int monster2Health = monster2.health;
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

        generateTreasurePoints(ghost1Targets, 1);
        generateTreasurePoints(ghost2Targets, 2);
        generateTreasurePoints(ghost3Targets, 3);

        Timer moveTimer = new Timer(100, e -> {
            suivreHero(monsterX, monsterY, false);
            suivreHero(monster2X, monster2Y, true);

            moveGhostToNextPoint(ghost1X, ghost1Y, ghost1Targets, ghost1TargetIndex, true, 1);
            moveGhostToNextPoint(ghost2X, ghost2Y, ghost2Targets, ghost2TargetIndex, true, 2);
            moveGhostToNextPoint(ghost3X, ghost3Y, ghost3Targets, ghost3TargetIndex, true, 3);

           
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
        int lvl = 0;
        labyDess.setNiveau(lvl);
        labyDess.paintComponent(g2);

        // if(hero.pouvoirPassage()){
        //     lvl++;
        //     labyDess.setNiveau(lvl);
        //     repaint();
        // }
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
                pushHeroBack(enemyX, enemyY);
                startCollision();
            }
        }
    }
    

    private void pushHeroBack(int attackerX, int attackerY) {
        int pushDistance = 20;
    
        
        int newHeroX = heroX;
        int newHeroY = heroY;
    
        if (attackerX < heroX) newHeroX += pushDistance;
        else if (attackerX > heroX) newHeroX -= pushDistance;
    
        if (attackerY < heroY) newHeroY += pushDistance;
        else if (attackerY > heroY) newHeroY -= pushDistance;
    
    
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

    private void generateTreasurePoints(int[][] targets, int ghostNumber) {
        char[][] maze = labyDess.getMaze();
        int[] treasureX = new int[2];
        int[] treasureY = new int[2];
        int treasureCount = 0;
        
        
        // position des tresaores 
        for (int y = 0; y < maze.length && treasureCount < 2; y++) {
            for (int x = 0; x < maze[0].length && treasureCount < 2; x++) {
                if (maze[y][x] == 'T') {
                    
                    
                    treasureX[treasureCount] = (x * 40)-60 ;  
                    treasureY[treasureCount] = (y * 40)-50 ;
                    
                    treasureCount++;
                }
            }
        }

      
       
        if (treasureCount == 0) {
            treasureX[0] = 560;
            treasureY[0] = 360;
            treasureX[1] = 800;
            treasureY[1] = 360;
        } else if (treasureCount == 1) {
            treasureX[1] = treasureX[0] + 240;
            treasureY[1] = treasureY[0];
        }

       // 2 ghosts pour le premier tresore et un pour lautre avec un rayon de 90
        int selectedTreasure = (ghostNumber <= 2) ? 0 : 1;
        int radius = 90;  

        // les points du cercle ou les ghosts vont tourner
        for (int i = 0; i < targets.length; i++) {
            double angle = 2 * Math.PI * i / targets.length;
            int pointX = treasureX[selectedTreasure] + (int)(radius * Math.cos(angle));
            int pointY = treasureY[selectedTreasure] + (int)(radius * Math.sin(angle));
            
            targets[i][0] = pointX;
            targets[i][1] = pointY;
           
        }
    }

    private void moveGhostToNextPoint(int ghostX, int ghostY, int[][] targets, int targetIndex, boolean isGhost, int ghostNumber) {
        int targetX = targets[targetIndex][0];
        int targetY = targets[targetIndex][1];
        int newGhostX = ghostX;
        int newGhostY = ghostY;
    
        double dx = targetX - ghostX;
        double dy = targetY - ghostY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance > GHOST_STEP) {
            newGhostX += (dx / distance) * GHOST_STEP;
            newGhostY += (dy / distance) * GHOST_STEP;
        }

        
        if (distance < GHOST_STEP * 2) {
            targetIndex = (targetIndex + 1) % targets.length;
        }

        
        if (isGhost) {
            if (ghostNumber == 1) {
                ghost1X = newGhostX;
                ghost1Y = newGhostY;
                ghost1TargetIndex = targetIndex;
            } else if (ghostNumber == 2) {
                ghost2X = newGhostX;
                ghost2Y = newGhostY;
                ghost2TargetIndex = targetIndex;
            } else if (ghostNumber == 3) {
                ghost3X = newGhostX;
                ghost3Y = newGhostY;
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
        if (heroLives <= 0) return;

        char[][] maze = labyDess.getMaze();
        final int moveStep = 5;
        final int mapWidth = maze[0].length * 40;  
        final int mapHeight = maze.length * 40;   
        
        // Sauvegarde de la position actuelle
        int newHeroX = heroX;
        int newHeroY = heroY;

        // Calcul de la nouvelle position
        switch (direction.toUpperCase()) {
            case "UP":
                if (heroY - moveStep >= 0) {
                    newHeroY -= moveStep;
                }
                break;
            case "DOWN":
                if (heroY + moveStep + heroHeight <= mapHeight) {
                    newHeroY += moveStep;
                }
                break;
            case "LEFT":
                if (heroX - moveStep >= 0) {
                    newHeroX -= moveStep;
                }
                break;
            case "RIGHT":
                if (heroX + moveStep + heroWidth <= mapWidth) {
                    newHeroX += moveStep;
                }
                break;
        }

        // Vérification des collisions avec les murs
        int cellX = newHeroX / 40;
        int cellY = newHeroY / 40;

        // Vérifier si la nouvelle position est valide
        if (cellX >= 0 && cellX < maze[0].length && 
            cellY >= 0 && cellY < maze.length && 
            maze[cellY][cellX] != '#') {
            heroX = newHeroX;
            heroY = newHeroY;
        }

        repaint();
    }
    

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        // Gérer le mouvement et l'attaque séparément
        if (keyCode == KeyEvent.VK_SPACE) {
            heroAttack();
        } else {
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
            }
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

