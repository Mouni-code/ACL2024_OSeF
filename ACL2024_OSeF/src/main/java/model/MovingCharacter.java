package model;

import java.util.LinkedList ;
import engine.GlobalKeyListener ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList ;

//import engine.GlobalKeyListener;

public class MovingCharacter {
    private LabyDess labyDess ;
    private int vitesse ;
    private int cellSize = 20 ;
    private GlobalKeyListener keyListener ;
    private LinkedList<Integer> Direction ;

    public MovingCharacter(){
        labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF\\ACL2024_OSeF\\src\\main\\java\\model\\Laby")) ;
        vitesse = labyDess.hero.getVit() ;
        labyDess.setPreferredSize(new Dimension(64*cellSize, 33*cellSize)) ; //j'ai testé ce qui remplissé mon ecran en entier. Peut etre pas compatible sur tous les pc 
        keyListener = new GlobalKeyListener() ;
        Direction = keyListener.direction ;

        JFrame frame = new JFrame("nomJeu") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(50*cellSize, 15*cellSize);
        frame.add(labyDess);

        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
        frame.requestFocus();

        Timer timer = new Timer(16, new ActionListener() { // Environ 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                int caseX, caseY ; //Comme on parle en pixel il faudra convertir en case pour le maze
                int pos_xHero = labyDess.hero.getPosition().getX() ; //Position x en pixels
                int pos_yHero = labyDess.hero.getPosition().getY() ;

                switch( Direction.getLast() ){//Direction.get(Direction.size() -1) ){
                    case 39 ://à droite
                        labyDess.hero.getPosition().setX( pos_xHero + vitesse ) ;
                        caseX = pos_xHero/cellSize ;
                        caseY = pos_yHero/cellSize ;
                        if ( labyDess.getMaze()[caseY + 1][caseX + 1] != ' ' && pos_yHero/20.0 -caseY != 0.0 || labyDess.getMaze()[caseY][caseX + 1] != ' ' ) { //Quand on va à droite on regarde si la case d'après est un obstacle
                            labyDess.hero.getPosition().setX( caseX*cellSize ) ;
                        }
                        break ;
                    case 37 ://à gauche
                        labyDess.hero.getPosition().setX( pos_xHero -vitesse ) ;
                        caseX = pos_xHero/cellSize ;
                        caseY = pos_yHero/cellSize ;
                        if ( labyDess.getMaze()[caseY][caseX -1] != ' ' || pos_yHero/20.0 -caseY != 0 && labyDess.getMaze()[caseY + 1][caseX -1] != ' ') {
                            labyDess.hero.getPosition().setX( caseX*cellSize ) ;
                        }
                        break ;
                    case 38 ://en haut
                        labyDess.hero.getPosition().setY( pos_yHero -vitesse ) ;
                        caseX = pos_xHero/cellSize ;
                        caseY = pos_yHero/cellSize ;
                        if ( labyDess.getMaze()[caseY -1][caseX] != ' ' || pos_xHero/20.0 -caseX != 0 && labyDess.getMaze()[caseY -1][caseX + 1] != ' ' ) {
                            labyDess.hero.getPosition().setY( caseY*cellSize ) ;
                        }
                        break ;
                    case 40 ://en bas
                    labyDess.hero.getPosition().setY( pos_yHero + vitesse ) ;
                    caseX = pos_xHero/cellSize ;
                    caseY = pos_yHero/cellSize ;
                    if ( labyDess.getMaze()[caseY + 1][caseX] != ' ' || pos_xHero/20.0 -caseX != 0 && labyDess.getMaze()[caseY + 1][caseX + 1] != ' ') {
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

    public void proxi(int X, int Y){
        int caseX = X/cellSize ; int caseY = Y/cellSize ;
        ArrayList<Character> listeCasePiege = new ArrayList<Character>() ; listeCasePiege.add('T') ;
        if( listeCasePiege.contains(labyDess.getMaze()[caseY][caseX + 1]) ){

        }
    }
}