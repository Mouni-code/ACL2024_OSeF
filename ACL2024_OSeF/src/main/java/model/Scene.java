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
    private Image  imagefond1;

    private ImageIcon icohero;
    private Image  imagehero;

    private int xfond;
    private int yfond;
    private int xfond1;
    private int yfond1;
    private int dx;
    private int dy;

    public Scene(){
        super();

        this.xfond = -50;
        this.yfond = 0;
        this.xfond1 = 1420;
        this.yfond1 = 980; 
        this.dx = 0;
        icofond = new ImageIcon(getClass().getResource("/images/fondecran.png"));
        this.imagefond = this.icofond.getImage();
        this.imagefond1 = this.icofond.getImage();
        icohero = new ImageIcon(getClass().getResource("/images/prince.png"));
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
        //Sauf qu'au final j'en ai pas besoin du déplacement du fond.
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics g2 = (Graphics2D)g;

        this.deplacementFond();
        g2.drawImage(this.imagefond, this.xfond, this.yfond, null);
        g2.drawImage(this.imagefond, this.xfond, this.yfond1, null);
        g2.drawImage(this.imagefond1, this.xfond1, this.yfond, null);
        g2.drawImage(this.imagefond1, this.xfond1, this.yfond1, null);
        g2.drawImage(this.imagehero, 100, 70, null);//changer la pos
    }
}