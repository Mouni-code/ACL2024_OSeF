package engine ;

import java.util.ArrayList;
import java.util.List ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GlobalKeyListener implements KeyListener {
    public List<Integer> direction ;
    private int n ;

    public GlobalKeyListener() {
        direction = new ArrayList<Integer>() ;
        direction.add(-1) ;
        n = direction.size() ;
        // Créer une fenêtre invisible pour capturer les événements clavier
        JFrame frame = new JFrame();
        frame.setSize(0, 0);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if( direction.get(n -1) != e.getKeyCode() ){
            direction.add(e.getKeyCode()) ;
        }
        System.out.println(direction.get(direction.size() -1)) ; //"Touche pressée : " + e.getKeyCode());
        //direction.remove(direction.size() -1) ;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        direction.removeIf(x -> x != -1 ) ;
        System.out.println(direction.get(direction.size() -1)) ; //"Touche relâchée : " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //direction.add(e.getKeyCode()) ;
        // Cette méthode n'est généralement pas utilisée pour l'écoute globale
    }

    public static void main(String[] args) {
        List<Integer> a = (new GlobalKeyListener()).direction ;
    }
}  