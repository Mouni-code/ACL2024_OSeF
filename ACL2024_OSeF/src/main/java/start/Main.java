package start;

import javax.swing.JFrame;

import engine.Chrono;
import model.Scene;

public class Main {

    public static Scene scene;

    public static void main(String[] args) {


        
        // Créer la fenêtre principale
        JFrame frame = new JFrame("Osef Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme l'application quand la fenêtre est fermée

        // Configurer la fenêtre
        frame.setSize(1160, 720); // Taille de la fenêtre 29*40 ET 18*40
        frame.setResizable(false); // Désactiver le redimensionnement
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        
        scene = new Scene(); 
        frame.setContentPane(scene); 
        scene.init();
       
        frame.setVisible(true);
        Thread chronoThread = new Thread(new Chrono());
        chronoThread.start();
    }

}
