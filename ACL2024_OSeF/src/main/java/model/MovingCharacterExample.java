package model ;

import java.util.List ;
import engine.GlobalKeyListener ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingCharacterExample {

    private static final int cellSize = 20 ; // Taille du personnage

    public static void main(String[] args) {
        
        // Créez le panneau du labyrinthe
        LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF\\ACL2024_OSeF\\src\\main\\java\\model\\Laby")) ;
        labyDess.setNiveau(2);
        int vitesse = labyDess.hero.getVit() ;
        labyDess.setPreferredSize(new Dimension(50*cellSize, 15*cellSize));
        

        JFrame frame = new JFrame("nomJeu") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(50*cellSize, 15*cellSize);
        frame.add(labyDess);

        GlobalKeyListener keyListener = new GlobalKeyListener();
        List<Integer> Direction = keyListener.direction ;
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
        frame.requestFocus();

        // Créez un Timer pour animer le personnage
        Timer timer = new Timer(16, new ActionListener() { // Environ 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                int caseX, caseY ; //Comme on parle en pixel il faudra convertir en case pour le maze
                int pos_xHero = labyDess.hero.getPosition().getX() ; //Position x en pixels
                int pos_yHero = labyDess.hero.getPosition().getY() ;

                switch( Direction.get(Direction.size() -1) ){
                    case 39 ://à droite
                        labyDess.hero.getPosition().setX( pos_xHero + vitesse ) ;
                        caseX = pos_xHero/cellSize ;
                        caseY = pos_yHero/cellSize ;
                        if ( labyDess.getMaze()[caseY][caseX + 1] != ' ' || labyDess.getMaze()[caseY + 1][caseX + 1] != ' ' ) { //Quand on va à droite on regarde si la case d'après est un obstacle
                            labyDess.hero.getPosition().setX( caseX*cellSize ) ;
                        }
                        break ;
                    case 37 ://à gauche
                        labyDess.hero.getPosition().setX( pos_xHero -vitesse ) ;
                        caseX = pos_xHero/cellSize ;
                        caseY = pos_yHero/cellSize ;
                        if ( labyDess.getMaze()[caseY][caseX -1] != ' ' || labyDess.getMaze()[caseY + 1][caseX -1] != ' ') {
                            labyDess.hero.getPosition().setX( caseX*cellSize ) ;
                        }
                        break ;
                    case 38 ://en haut
                        labyDess.hero.getPosition().setY( pos_yHero -vitesse ) ;
                        caseX = pos_xHero/cellSize ;
                        caseY = pos_yHero/cellSize ;
                        if ( labyDess.getMaze()[caseY -1][caseX] != ' ' || labyDess.getMaze()[caseY -1][caseX + 1] != ' ' ) {
                            labyDess.hero.getPosition().setY( caseY*cellSize ) ;
                        }
                        break ;
                    case 40 ://en bas
                    labyDess.hero.getPosition().setY( pos_yHero + vitesse ) ;
                    caseX = pos_xHero/cellSize ;
                    caseY = pos_yHero/cellSize ;
                    if ( labyDess.getMaze()[caseY + 1][caseX] != ' ' || labyDess.getMaze()[caseY + 1][caseX + 1] != ' ') {
                        labyDess.hero.getPosition().setY( caseY*cellSize ) ;
                    }
                    break ;
                    
                    default :
                        
                        break ;
                }
            labyDess.repaint(); // Redessine le panneau
            }
        }) ;
        timer.start(); // Démarre le timer
        
        frame.pack();
        frame.setVisible(true);
    }
}