package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    private ImageIcon icoYouDied;
    private Image imageYouDied;

    private int heroX = 100; // Position initiale du héros
    private int heroY = 70;
    private int heroWidth = 50; // Largeur approximative du héros
    private int heroHeight = 50; // Hauteur approximative du héros

    private int monsterX = 300; // Position initiale du monstre
    private int monsterY = 200;
    private int monsterHealth = 100; // Santé du monstre
    private int monsterMaxHealth = 100;

    // Santé et vies du héros
    private int heroHealth = 100; // Santé actuelle
    private int heroMaxHealth = 100; // Santé maximale
    private int heroLives = 3; // Nombre de vies

    // Gestion de la collision
    private boolean isCollision = false; // Indique si une collision est en cours
    private Timer collisionTimer; // Timer pour gérer la durée de la collision
    private int collisionDuration = 500; // Durée de la collision (en millisecondes)

    

    public Scene() {
        super();

        // Charger les images
        icofond = new ImageIcon(getClass().getResource("/images/fondecran.png"));
        this.imagefond = this.icofond.getImage();
        icohero = new ImageIcon(getClass().getResource("/images/heroagain.png"));
        this.imagehero = this.icohero.getImage();
        icomonstre = new ImageIcon(getClass().getResource("/images/monster.png"));
        this.imagemonstre = this.icomonstre.getImage();

        // Charger l'image "You Died"
        icoYouDied = new ImageIcon(getClass().getResource("/images/youdied.png"));
        this.imageYouDied = this.icoYouDied.getImage();

        // Configuration du panneau
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);

        // Timer pour déplacer le monstre vers le héros
        Timer moveTimer = new Timer(100, e -> {
            if (!isCollision) { // Le monstre ne bouge pas pendant la collision
                suivreHero();
            }
            repaint(); // Redessine la scène à chaque intervalle
        });
        moveTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Dessiner le fond
        g2.drawImage(this.imagefond, 0, 0, null);

        // Dessiner le labyrinthe
        drawLaby(g2);

        // Si le héros est mort, afficher "You Died"
        if (heroLives <= 0) {
            int imageWidth = imageYouDied.getWidth(null);
            int imageHeight = imageYouDied.getHeight(null);

            int centerX = (this.getWidth() - imageWidth) / 2; 
            int centerY = (this.getHeight() - imageHeight) / 2; 

            g2.drawImage(this.imageYouDied, centerX, centerY, null); 
            return;
        }

       
        if (heroLives > 0) {
            g2.drawImage(this.imagehero, heroX, heroY, null);
        }

        
        if (monsterHealth > 0) {
            g2.drawImage(this.imagemonstre, monsterX, monsterY, null);

            
            drawMonsterHealthBar(g2);
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

    private void drawMonsterHealthBar(Graphics2D g2) {
        int barX = monsterX, barY = monsterY - 20, barWidth = 100, barHeight = 10;
        int filledWidth = (int) ((double) monsterHealth / monsterMaxHealth * barWidth);

      
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
    LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF/src/main/java/model/Laby"));
    

    private void drawLaby(Graphics2D g2) {
        labyDess.setNiveau(0);
        labyDess.paintComponent(g2); 
    }

    private void suivreHero() {
       
        if (monsterHealth <= 0) {
            return;
        }

        if (Math.abs(monsterX - heroX) < 20 && Math.abs(monsterY - heroY) < 20) {
            
            if (!isCollision) {
                startCollision();
            }
            return;
        }

        
        if (monsterX < heroX) {
            monsterX += 5;
        } else if (monsterX > heroX) {
            monsterX -= 5;
        }

        if (monsterY < heroY) {
            monsterY += 5;
        } else if (monsterY > heroY) {
            monsterY -= 5;
        }
    }

    private void startCollision() {
        if (monsterHealth <= 0) {
            return;
        }
        isCollision = true;
        heroHealth = Math.max(0, heroHealth - 10);
        if (heroHealth == 0) {
            heroLives--; 
            if (heroLives > 0) {
                heroHealth = heroMaxHealth; 
            }
        }  
        collisionTimer = new Timer(collisionDuration, e -> {
            isCollision = false;
            collisionTimer.stop();
        });
        collisionTimer.setRepeats(false);
        collisionTimer.start();
    }

    private void heroAttack() {
        
        if (Math.abs(monsterX - heroX) < 50 && Math.abs(monsterY - heroY) < 50) {
            monsterHealth = Math.max(0, monsterHealth - 20); 
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
                if (heroY >0) { // Limites hautes
                    for (int x = heroX; x < heroX + heroWidth; x += cellSize) {
                        int caseXToCheck = x / cellSize;
                        int caseYToCheck = (heroY + 4*moveStep) / cellSize; // Position future (haut)
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
                        int caseYToCheck = (heroY + 4*moveStep + heroHeight) / cellSize; // Position future (bas)
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
     //A regler encore
    @Override
    public void keyPressed(KeyEvent e) {
        // Récupérer la touche appuyée
        int keyCode = e.getKeyCode();
    
        switch (keyCode) {
            case KeyEvent.VK_UP: // Flèche haut
                moveHero("UP");
                break;
    
            case KeyEvent.VK_DOWN: // Flèche bas
                moveHero("DOWN");
                break;
    
            case KeyEvent.VK_LEFT: // Flèche gauche
                moveHero("LEFT");
                break;
    
            case KeyEvent.VK_RIGHT: // Flèche droite
                moveHero("RIGHT");
                break;
    
            default:
                System.out.println("Touche non prise en charge !");
                break;

            case KeyEvent.VK_SPACE: // Attaque
                // Action d'attaque déclenchée
                heroAttack();
            break;
        }
    
        // Rafraîchir l'écran après le déplacement
        repaint();
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }
}
