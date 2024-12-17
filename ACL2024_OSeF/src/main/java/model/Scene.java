package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Scene extends JPanel implements KeyListener {

    Hero hero = new Hero(100, 70, 40 , 40, 3, 100, 20, 50, 50,0);
    Monster monster = new Monster(350, 80, 40, 40, 1, 100, 20, 50, 50, false);
    Monster monster2 = new Monster(1060, 300, 50, 50, 1, 100, 20, 50,50, true);
    Ghost ghost1 = new Ghost(400, 400, 50, 50, 1, 100, 20, 10,10);
    Ghost ghost2 = new Ghost(600, 100,50, 50, 1, 100, 20, 10,10);
    Ghost ghost3 = new Ghost(200, 500, 50, 50, 1, 100, 20, 10,10);


    HashSet<Element> murs = new HashSet<>();
    HashSet<Element> portes = new HashSet<>();
    HashSet<Element> princesses = new HashSet<>();
    HashSet<Element> tresors = new HashSet<>();
    char[] directions = {'U', 'D', 'L', 'R'}; //up down left right
    int tileSize = 40;
    int heroX = hero.startX;
    int heroY =  hero.startY;
    int monsterX = monster.startX;
    int monsterY = monster.startY;
    int monsterHealth = monster.health;
    int ghost2X = ghost2.startX;
    int ghost2Y = ghost2.startY;
    int ghost2Health = ghost2.health;
    private final ImageIcon icofond;
    private final Image imagefond;

    private final ImageIcon icohero;
    private final Image imagehero;

    private final ImageIcon icomonstre;
    private final Image imagemonstre;

    private final ImageIcon icoghost;
    private final Image imageghost;

    private final ImageIcon icoYouDied;
    private final Image imageYouDied;

    private final ImageIcon icoVictory;
    private final Image imageVictory;

    private final ImageIcon icoPierre;
    private final Image imagePierre;

    private final ImageIcon icotresor ;
    private final Image imagetresor;

    private final ImageIcon icopassage;
    private final Image imagepassage;

    private final ImageIcon icoprincesse;
    private final Image imageprincesse;
    // Parcourir le labyrinthe et dessiner les murs
     
    int ghost1X = ghost1.startX;
    int ghost1Y = ghost1.startY;
    int ghost1Health = ghost1.health;
    int ghost3X = ghost3.startX;
    int ghost3Y = ghost3.startY;
    int ghost3Health = ghost3.health;
    int heroHealth = hero.health;
    int heroMaxHealth = 100;
    int heroLives = hero.lives;
    private final int monsterMaxHealth = 100;
    private int monster2Health = monster2.health;
    private final int monster2MaxHealth = 100;
    private final int ghost1MaxHealth = 100;
    private final int[][] ghost1Targets = new int[5][2];
    private int ghost1TargetIndex = 0;
    private final int ghost2MaxHealth = 100;
    private final int[][] ghost2Targets = new int[5][2];
    private int ghost2TargetIndex = 0;
    private final int ghost3MaxHealth = 100;
    private final int[][] ghost3Targets = new int[5][2];
    private int ghost3TargetIndex = 0;

    private static final int MAX_DISTANCE = 300;
    private static final int MAP_WIDTH = 800;
    private static final int GHOST_STEP = 5;

    
    private int currentLevel = 0;
    private final boolean gameRunning = true;


    private boolean isCollision = false;
    private Timer collisionTimer;
    private final int collisionDuration = 500;

    private final Random random = new Random();

    public final LabyDess labyDess= new LabyDess(new Labyrinthe("C:/Users/Mouna/OneDrive/Desktop/ACL2024_OSeF/ACL2024_OSeF/src/main/resources/Laby"));
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

        icoPierre = new ImageIcon(getClass().getResource("/images/pierre.png"));
        this.imagePierre = icoPierre.getImage();

        icotresor = new ImageIcon(getClass().getResource("/images/coffre.png"));
        this.imagetresor = icotresor.getImage();

        icopassage = new ImageIcon(getClass().getResource("/images/porte.png"));
        this.imagepassage = icopassage.getImage();

        icoprincesse = new ImageIcon(getClass().getResource("/images/princesse.png"));
        this.imageprincesse = icoprincesse.getImage();

        icoVictory = new ImageIcon(getClass().getResource("/images/victory.png"));
        this.imageVictory = icoVictory.getImage();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        
        generateTreasurePoints(ghost1Targets, 1);
        generateTreasurePoints(ghost2Targets, 2);
        generateTreasurePoints(ghost3Targets, 3);

        Timer moveTimer = new Timer(100, e -> {
            suivreHero(monster,monster.x, monster.y, false);
            //suivreHero(monster2, monster2.x, monster2.y, true);

            moveGhostToNextPoint(ghost1X, ghost1Y, ghost1Targets, ghost1TargetIndex, true, 1);
            moveGhostToNextPoint(ghost2X, ghost2Y, ghost2Targets, ghost2TargetIndex, true, 2);
            moveGhostToNextPoint(ghost3X, ghost3Y, ghost3Targets, ghost3TargetIndex, true, 3);

           
            if (monsterHealth > 0) attackHero(monster.x, monster.y);
            if (monster2Health > 0) attackHero(monster2.x, monster2.y);
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        char[][] maze = labyDess.getMaze();
        int cellSize = 40;
        g2.drawImage(this.imagefond, 0, 0, null);
        drawLaby(currentLevel);
        if (heroLives <= 0) {
            int imageWidth = imageYouDied.getWidth(null);
            int imageHeight = imageYouDied.getHeight(null);

            int centerX = (this.getWidth() - imageWidth) / 2;
            int centerY = (this.getHeight() - imageHeight) / 2;

            g2.drawImage(this.imageYouDied, centerX, centerY, null);
            return;
        }
            if(Liberee()){
                int imageWidth = imageVictory.getWidth(null);
                int imageHeight = imageVictory.getHeight(null);
    
                int centerX = (this.getWidth() - imageWidth) / 2;
                int centerY = (this.getHeight() - imageHeight) / 2;
    
                g2.drawImage(this.imageVictory, centerX, centerY, null);
                return;
            }
        g2.drawImage(this.imagehero, hero.x, hero.y, Element.WIDTH, Element.HEIGHT, null);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == '#') { // Cas des murs
                    int x = col * cellSize;
                    int y = row * cellSize;
                    Element wall = new Element(x, y, cellSize, cellSize);
                    g.drawImage(imagePierre, x, y, cellSize, cellSize, this);
                    murs.add(wall);
                }
                if (maze[row][col] == 'T') { // Cas des tresor
                    int x = col * cellSize;
                    int y = row * cellSize;
                    Element tresor = new Element(x, y, cellSize, cellSize);
                    g.drawImage(imagetresor, x, y, cellSize, cellSize, this);
                    tresors.add(tresor);
                }
                if (maze[row][col] == 'P') { // Cas des passages
                    int x = col * cellSize;
                    int y = row * cellSize;
                    Element porte = new Element(x, y, cellSize, cellSize);
                    g.drawImage(imagepassage, x, y, cellSize, cellSize, this);
                    portes.add(porte);
                }
                if (maze[row][col] == 'R') { // Cas des passages
                    int x = col * cellSize;
                    int y = row * cellSize;
                    Element princesse = new Element(x, y, cellSize, cellSize);
                    g.drawImage(imageprincesse, x, y, 50, 50, this);
                    princesses.add(princesse);
                }
            }
        }
        if (monsterHealth > 0) {
            g2.drawImage(this.imagemonstre, monster.x, monster.y,80,50, null);
            drawMonsterHealthBar(g2, monster.x, monster.y, monsterHealth, monsterMaxHealth);
        }
        if (monster2Health > 0) {
            g2.drawImage(this.imagemonstre, monster2.x, monster2.y,80, 50, null);
            drawMonsterHealthBar(g2, monster2.x, monster2.y, monster2Health, monster2MaxHealth);
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


    public boolean Liberee(){
        boolean ok = false;
        if(labyDess.getNiveau()==1 && hero.inventaire == 2){
            for(Element princesse:princesses){
                princesse.delivered = true;
                ok = true;
            }
        }
        return ok;
    }
    public boolean Success() {
        if (hero.inventaire >= 2) { // Vérifie si au moins 2 étoiles ont été ramassées
            for (Element porte : portes) {
                if (hero.x == porte.x && hero.y == porte.y) {
                    System.out.println("Position success");
                    return true;
                }
            }
        }
        return false;
    }
    public void  passage(){
        if(Success() && etoiles()==2) labyDess.setNiveau(1);
    }
    //A regler, il prend toujours du même trésor
    public int etoiles() {
        int etoilesCollectees = 0;
        for (Element tresor : tresors) {
            if (tresor.inventaireTresor!=0) {
                if (hero.x == tresor.x && hero.y == tresor.y) {
                    System.out.println("La position du joueur a atteint celle du trésor");
                    hero.inventaire++;
                    tresor.setinventaireTresor(0);
                    tresor.ramasse = true; 
                    System.out.println("l'inventaire est de : "+hero.inventaire+"etoiles");
                    etoilesCollectees++;
                    if (etoilesCollectees >= 1) { 
                        break;
                    }
                }
            }
        }
        return hero.inventaire;
    }

    public void resetGame(){
        hero.reset();
        monster.reset();
        // monster.reset();
        monster2.reset();
        ghost1.reset();
         ghost2.reset();
        ghost3.reset();
        hero.setinventaire(0);
    }

    private void drawLaby(int lvl) {
        labyDess.setNiveau(lvl);
        //passage();
        // if (currentLevel < 1 && gameRunning) { // Niveau 2 et 3 INDISPO
        //             labyDess.setNiveau(currentLevel);
        //             if (Success()) {
        //                 currentLevel++;
        //                  labyDess.setNiveau(currentLevel);
        // }
        Timer gameTimer = new Timer(100, e -> {
            if (currentLevel < 1 && gameRunning) { // Niveau 2 et 3 INDISPO
                labyDess.setNiveau(currentLevel);
                if (Success()) {
                    currentLevel++;
                     labyDess.setNiveau(currentLevel);
                    // char newDirection = directions[random.nextInt(4)];
                    // updateDirection(newDirection, monster);
                    // updateDirection(newDirection, monster2);
                    // suivreHero(monster, monster.x, monster.y, false);
                    // suivreHero(monster2, monster2.x, monster2.y, true);
                    resetGame();
                }
        //         // updateVelocity(monster);
        //         // updateVelocity(monster2);
        //         // suivreHero(monster, monster.x, monster.y, false);
        //         // suivreHero(monster2, monster2.x, monster2.y, true);
    
        //         // //Déplacez les fantômes
        //         // moveGhostToNextPoint(ghost1X, ghost1Y, ghost1Targets, ghost1TargetIndex, true, 1);
        //         // moveGhostToNextPoint(ghost2X, ghost2Y, ghost2Targets, ghost2TargetIndex, true, 2);
        //         // moveGhostToNextPoint(ghost3X, ghost3Y, ghost3Targets, ghost3TargetIndex, true, 3);
               
    
        //         // Attaquez le héros si les monstres sont en vie
        //         // if (monsterHealth > 0) attackHero(monster.x, monster.y);
        //         // if (monster2Health > 0) attackHero(monster2.x, monster2.y);
        //         // if (ghost1Health > 0) attackHero(ghost1.x, ghost1.y);
        //         // if (ghost2Health > 0) attackHero(ghost2.x, ghost2.y);
        //         // if (ghost3Health > 0) attackHero(ghost3.x, ghost3.y);
             }
 });
         gameTimer.start();
    }

    private void attackHero(int enemyX, int enemyY) {
        if (enemyX == monster.x && monsterHealth <= 0) return;
        if (enemyX == monster2.x && monster2Health <= 0) return;
        if (enemyX == ghost1.x && ghost1Health <= 0) return;
        if (enemyX == ghost2.x && ghost2Health <= 0) return;
        if (enemyX == ghost3.x && ghost3Health <= 0) return;
    
        int distanceX = Math.abs(enemyX - hero.x);
        int distanceY = Math.abs(enemyY - hero.y);
    
        if (distanceX < 50 && distanceY < 50) {
            if (!isCollision) {
                //pushHeroBack(enemyX, enemyY);
                startCollision();
            }
        }
    }
    
   // Méthode pour déplacer le monstre toutes les secondes (ou un autre intervalle)
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

    private void suivreHero(Monster monster, int currentMonsterX, int currentMonsterY, boolean isSecondMonster) {
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
        } else {
            monsterX = currentMonsterX;
            monsterY = currentMonsterY;
        }
    }

    public boolean collision(Element a, Element b) {
        return  a.x < b.x + b.WIDTH &&
                a.x + a.WIDTH > b.x &&
                a.y < b.y + b.HEIGHT &&
                a.y + a.HEIGHT > b.y;
    }
    
    public void updateDirection(char direction, Element element) {
        char prevDirection = element.direction;
        element.direction = direction;
        updateVelocity(element);
        element.x += element.velocityX;
        element.y += element.velocityY;
        for (Element wall : murs) {
                 if (collision(element, wall)) {
                     // Revert movement if collision detected
                    element.x -= element.velocityX;
                     element.y -= element.velocityY;
                     element.direction = prevDirection;
                     updateVelocity(element);
                    break;
                 }
              }
       
    }
     void updateVelocity(Element element) {
        switch (element.direction) {
            case 'U':
                element.velocityX = 0;
                element.velocityY = - tileSize/4;
                break;
            case 'D':
                element.velocityX = 0;
                element.velocityY = tileSize/4;
                break;
            case 'L':
                element.velocityX = -tileSize/4;
                element.velocityY = 0;
                break;
            case 'R':
                element.velocityX = +tileSize/4;
                element.velocityY = 0;
                break;
            default:
                break;
        }
    }
    public void move(){
        Monster[] monsters = {monster, monster2};
        hero.x += hero.velocityX;
        hero.y += hero.velocityY;
          for(Element wall : murs){
              if(collision(hero, wall)){
                hero.x -= hero.velocityX;
                hero.y -= hero.velocityY;
                break;
              }
          }
          for(Monster monster : monsters){
            if (collision(monster, hero)) {
                attackHero(monster.x , monster.y);
            }
            // if (monster.y == tileSize*9 && monster.direction != 'U' && monster.direction != 'D') {
            //     updateDirection('U', monster);
            // }
            monster.x += monster.velocityX;
            monster.y += monster.velocityY;
            for (Element wall : murs) {
                if (collision(monster, wall) || monster.x <= 0 || monster.x + monster.WIDTH >= MAP_WIDTH) {
                    monster.x -= monster.velocityX;
                    monster.y -= monster.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    updateDirection(newDirection, monster);
                }
            }

          }
            if (collision(monster2, hero)) {
                attackHero(monster2.x , monster2.y);
            }
            if (monster2.y == tileSize*9 && monster2.direction != 'U' && monster2.direction != 'D') {
                updateDirection('U', monster2);
            }
            monster2.x += monster2.velocityX;
            monster2.y += monster2.velocityY;
            for (Element wall : murs) {
                if (collision(monster2, wall) || monster2.x <= 0 || monster2.x + monster2.WIDTH >= 1160) {
                    monster2.x -= monster2.velocityX;
                    monster2.y -= monster2.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    updateDirection(newDirection, monster2);
                }
            }
    }
    
    
    // public void resetPositions(){
    //     hero.reset();
    //     monster.reset();
    //     monster2.reset();
    // }

    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            updateDirection('U', hero);
            move();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            updateDirection('D', hero);
            move();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            updateDirection('L', hero);
            move();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            updateDirection('R', hero);
            move();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            heroAttack();
        }
        else if (e.getKeyCode() == KeyEvent.VK_G) {
            etoiles();
        }
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            Success();
        }
    }
    

    private void heroAttack() {
        // Réduire la santé uniquement si le monstre est vivant
        if (Math.abs(monster.x - hero.x) < 50 && Math.abs(monster.y - hero.y) < 50 && monsterHealth > 0) {
            monsterHealth = Math.max(0, monsterHealth - 20);
        }
        if (Math.abs(monster2.x - hero.x) < 50 && Math.abs(monster2.y - hero.y) < 50 && monster2Health > 0) {
            monster2Health = Math.max(0, monster2Health - 20);
        }
        if (Math.abs(ghost1.x - hero.x) < 50 && Math.abs(ghost1.y - hero.y) < 50 && ghost1Health > 0) {
            ghost1Health = Math.max(0, ghost1Health - 20);
        }
        if (Math.abs(ghost2.x - hero.x) < 50 && Math.abs(ghost2.y - hero.y) < 50 && ghost2Health > 0) {
            ghost2Health = Math.max(0, ghost2Health - 20);
        }
        if (Math.abs(ghost3.x - hero.x) < 50 && Math.abs(ghost3.y - hero.y) < 50 && ghost3Health > 0) {
            ghost3Health = Math.max(0, ghost3Health - 20);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}