package model;

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

    private int heroX = 100;
    private int heroY = 70;
    private int heroWidth = 50;
    private int heroHeight = 50;

    private int monsterX = 300;
    private int monsterY = 200;
    private int monsterHealth = 100;
    private int monsterMaxHealth = 100;

    private int monster2X = 500;
    private int monster2Y = 300;
    private int monster2Health = 100;
    private int monster2MaxHealth = 100;

    private int ghost1X = 400, ghost1Y = 400;
    private int ghost1Health = 100, ghost1MaxHealth = 100;
    private int[][] ghost1Targets = new int[5][2];
    private int ghost1TargetIndex = 0;

    private int ghost2X = 600, ghost2Y = 100;
    private int ghost2Health = 100, ghost2MaxHealth = 100;
    private int[][] ghost2Targets = new int[5][2];
    private int ghost2TargetIndex = 0;

    private int ghost3X = 200, ghost3Y = 500;
    private int ghost3Health = 100, ghost3MaxHealth = 100;
    private int[][] ghost3Targets = new int[5][2];
    private int ghost3TargetIndex = 0;

    private int heroHealth = 100;
    private int heroMaxHealth = 100;
    private int heroLives = 3;

    private static final int MAX_DISTANCE = 300;
    private static final int MAP_WIDTH = 800;
    private static final int MAP_HEIGHT = 600;
    private static final int GHOST_STEP = 5;

    private boolean isCollision = false;
    private Timer collisionTimer;
    private int collisionDuration = 500;

    private Random random = new Random();

    private LabyDess labyDess;

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

        labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF/src/main/java/model/Laby"));

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

    private boolean isWalkable(int x, int y) {
        int cellSize = 40;

        int cellX1 = x / cellSize;
        int cellY1 = y / cellSize;
        int cellX2 = (x + heroWidth - 1) / cellSize;
        int cellY2 = (y + heroHeight - 1) / cellSize;

        if (cellY1 < 0 || cellY2 >= labyDess.getMaze().length ||
            cellX1 < 0 || cellX2 >= labyDess.getMaze()[0].length) {
            return false;
        }

        char[][] maze = labyDess.getMaze();
        return maze[cellY1][cellX1] != '#' &&
               maze[cellY1][cellX2] != '#' &&
               maze[cellY2][cellX1] != '#' &&
               maze[cellY2][cellX2] != '#';
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


    public void moveHero(String direction) {
        if (heroLives <= 0) return;

        int newX = heroX, newY = heroY;
        int moveStep = 10;
        boolean collision = false;

        switch (direction.toUpperCase()) {
            case "UP":
                newY -= moveStep;
                break;
            case "DOWN":
                newY += moveStep;
                break;
            case "LEFT":
                newX -= moveStep;
                break;
            case "RIGHT":
                newX += moveStep;
                break;
            default:
                System.out.println("Direction non valide !");
                break;
        }

        if (!isWalkable(newX, newY)) {
            collision = true;
        } else {
            heroX = newX;
            heroY = newY;
        }

        if (collision) {
            System.out.println("Collision détectée !");
        }

        System.out.println("Position actuelle : (" + heroX + ", " + heroY + ")");
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

