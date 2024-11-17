package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Chrono;
import engine.Keyboard;

public class Scene extends JPanel {

    private ImageIcon icofond;
    private Image  imagefond;

    
    private ImageIcon icohero;
    private Image  imagehero;

    private int fond1;
    private int fond2;
    private int dx;
    private int dy;


    public Scene(){
        super();

        this.fond1 = -50;
        this.fond2 = -50;
        this.dx = 0;
        icofond = new ImageIcon(getClass().getResource("/images/fondecran.png"));
        this.imagefond = this.icofond.getImage();
        icohero = new ImageIcon(getClass().getResource("/images/hero.png"));
        this.imagehero = this.icohero.getImage();
        
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Keyboard());
        Thread chronoEcran = new Thread(new Chrono());
        chronoEcran.start();

    }
    public int getDx() {
        return dx;
    }
    public void setDx(int dx){
        this.dx = dx;
    }
    public int getDy(){
        return dy;
    }
    public void setDy(int dy){
        this.dy=dy;
    }

    public void deplacementFond(){
        this.fond1 = this.fond1 - this.dx;
        this.fond2 = this.fond2 - this.dy;
    }

    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;
        
        this.deplacementFond();

        g2.drawImage(this.imagefond, this.fond1, this.fond2, null);
        g2.drawImage(this.imagehero, 100, 70, null);
    }
}
