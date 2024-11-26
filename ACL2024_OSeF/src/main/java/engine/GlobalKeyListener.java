package engine ;

import java.util.LinkedList;
//import java.awt.desktop.SystemSleepEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GlobalKeyListener implements KeyListener {
    public LinkedList<Integer> direction ;
    int jeton ;
    public boolean press ;
    
    public GlobalKeyListener() {
        press = false ;
        direction = new LinkedList<Integer>() ;
        direction.add(-1) ;
        jeton = 0 ;

        // Créer une fenêtre invisible pour capturer les événements clavier pour les tests claviers seulemnts
        JFrame frame = new JFrame();
        frame.setSize(0, 0);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {//prendre ds jetons // Essayer de l'enlever juste avant qu'il le remette
        if( direction.getLast() != e.getKeyCode() ){
            jeton += 1 ;
            direction.add(e.getKeyCode()) ;
        }
        System.out.println("Pr_jet." + jeton + direction ) ; //"Touche pressée : " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        jeton -= 1 ;//ici
        if( jeton < 0 ){
            jeton = 0 ;
        }
        direction.removeIf(x -> x != -1 && x == direction.getLast() ) ;//ici
        /*if( n == 0 ){
            direction.add(-1) ;
        }*/
        //direction.removeIf(x -> x != -1 ) ;//OK
        System.out.println("Rl_jet." + jeton + direction) ; //"Touche relâchée : " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        direction.add(e.getKeyCode()) ;
        jeton += 1 ;
        direction.removeLast() ;
        jeton -= 1 ;
    }

    public static void main(String[] arg){
        new GlobalKeyListener() ;
    }
}  