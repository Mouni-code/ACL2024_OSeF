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
        icohero = new ImageIcon(getClass().getResource("/images/prince.png"));
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

    private void drawLaby(Graphics2D g2) {
        LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF/src/main/java/model/Laby"));
        labyDess.setNiveau(2);
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

    @Override
    public void keyPressed(KeyEvent e) {
        if (heroLives <= 0) {
            return; 
        }

        int mapWidth = this.getWidth();
        int mapHeight = this.getHeight();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (heroY > 0) {
                    heroY -= 10;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (heroY + heroHeight < mapHeight) {
                    heroY += 10;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (heroX > 0) {
                    heroX -= 10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (heroX + heroWidth < mapWidth) {
                    heroX += 10;
                }
                break;
            case KeyEvent.VK_SPACE: // Attaque
                heroAttack();
                break;
        }
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
