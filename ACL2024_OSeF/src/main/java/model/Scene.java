package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Scene extends JPanel implements KeyListener {

    private ImageIcon icofond;
    private Image imagefond;
    private Image imagefond1;

    private ImageIcon icohero;
    private Image imagehero;

    private ImageIcon icomonstre;
    private Image imagemonstre;

    private int xfond, yfond, xfond1, yfond1;
    private int dx, dy;

    // Position du héros
    private int heroX = 100;
    private int heroY = 70;

    // Position du monstre
    private int monsterX = 300;
    private int monsterY = 200;

    public Scene() {
        super();

        this.xfond = -50;
        this.yfond = 0;
        this.xfond1 = 1420;
        this.yfond1 = 980;
        this.dx = 0;

        // Charger les images
        icofond = new ImageIcon(getClass().getResource("/images/fondecran.png"));
        this.imagefond = this.icofond.getImage();
        this.imagefond1 = this.icofond.getImage();
        icohero = new ImageIcon(getClass().getResource("/images/prince.png"));
        this.imagehero = this.icohero.getImage();
        icomonstre = new ImageIcon(getClass().getResource("/images/monster.png"));
        this.imagemonstre = this.icomonstre.getImage();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
    }

    public void deplacementFond() {
        this.xfond = this.xfond - this.dx;
        this.xfond1 = this.xfond1 - this.dx;

        this.yfond = this.yfond - this.dy;
        this.yfond1 = this.yfond1 - this.dy;

        // Repositionnement horizontal
        if (this.xfond <= -1470) this.xfond = 1470;
        if (this.xfond1 <= -1470) this.xfond1 = 1470;
        if (this.xfond >= 1470) this.xfond = -1470;
        if (this.xfond1 >= 1470) this.xfond1 = -1470;

        // Repositionnement vertical
        if (this.yfond <= -980) this.yfond = 980;
        if (this.yfond1 <= -980) this.yfond1 = 980;
        if (this.yfond >= 980) this.yfond = -980;
        if (this.yfond1 >= 980) this.yfond1 = -980;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Affichage du fond
        this.deplacementFond();
        g2.drawImage(this.imagefond, this.xfond, this.yfond, null);
        g2.drawImage(this.imagefond, this.xfond, this.yfond1, null);
        g2.drawImage(this.imagefond1, this.xfond1, this.yfond, null);
        g2.drawImage(this.imagefond1, this.xfond1, this.yfond1, null);

        // Affichage du héros
        g2.drawImage(this.imagehero, heroX, heroY, null);

        // Affichage du monstre
        g2.drawImage(this.imagemonstre, monsterX, monsterY, null);
    }

    // Méthodes pour gérer les événements clavier
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    // Flèche haut
                heroY -= 10;
                break;
            case KeyEvent.VK_DOWN:  // Flèche bas
                heroY += 10;
                break;
            case KeyEvent.VK_LEFT:  // Flèche gauche
                heroX -= 10;
                break;
            case KeyEvent.VK_RIGHT: // Flèche droite
                heroX += 10;
                break;
            case KeyEvent.VK_W:     // Touche 'W' pour déplacer le monstre vers le haut
                monsterY -= 10;
                break;
            case KeyEvent.VK_S:     // Touche 'S' pour déplacer le monstre vers le bas
                monsterY += 10;
                break;
            case KeyEvent.VK_A:     // Touche 'A' pour déplacer le monstre à gauche
                monsterX -= 10;
                break;
            case KeyEvent.VK_D:     // Touche 'D' pour déplacer le monstre à droite
                monsterX += 10;
                break;
        }
        repaint(); // Redessine la scène pour appliquer les changements
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé dans cet exemple
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé dans cet exemple
    }
}
