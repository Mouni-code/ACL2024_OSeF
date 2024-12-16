package model ;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MovingCharacterExample {

    private static final int cellSize = 20 ; // Taille du personnage
    //private static int x = 100 ; // Position initiale en x
    //private static int y = 100 ; // Position initiale en y

    public static void main(String[] args) {
        
        // Créez le panneau du labyrinthe
        LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF\\ACL2024_OSeF\\src\\main\\java\\model\\Laby")) ;
        labyDess.setNiveau(2);
        labyDess.setPreferredSize(new Dimension(50*cellSize, 15*cellSize));
        

        JFrame frame = new JFrame("nomJeu") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(50*cellSize, 15*cellSize);
        frame.add(labyDess);
        
        // Créez un Timer pour animer le personnage
        Timer timer = new Timer(16, new ActionListener() { // Environ 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos_xHero = labyDess.hero.position.getX() ; //Position x en pixels
                int pos_yHero = labyDess.hero.position.getY() ;
                //x += 7 :
                labyDess.hero.position.setX( pos_xHero + 7 ) ; //Avance de 7 pixels
                int caseX = pos_xHero/cellSize ;
                int caseY = pos_yHero/cellSize ;
                
                if ( labyDess.getMaze()[caseY][caseX + 1] != ' ' ) { //Quand on va à droite on regarde si la case d'après est un obstacle
                    //x = -CHARACTER_SIZE;
                    labyDess.hero.position.setX( caseX*cellSize ) ;
                }
                labyDess.repaint(); // Redessine le panneau
            }
        }) ;
        timer.start(); // Démarre le timer
        
        frame.pack();
        frame.setVisible(true);
    }
}