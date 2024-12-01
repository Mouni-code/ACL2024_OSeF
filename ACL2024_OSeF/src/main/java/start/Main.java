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
        frame.setSize(1000, 700); // Taille de la fenêtre
        frame.setResizable(false); // Désactiver le redimensionnement
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran

        // Créer une instance de la scène et l'ajouter à la fenêtre
        scene = new Scene(); // La scène gère l'affichage et l'interactivité
        frame.setContentPane(scene); // Ajouter la scène comme contenu principal

        // Afficher la fenêtre
        frame.setVisible(true);
        Thread chronoThread = new Thread(new Chrono());
        chronoThread.start();
    }

}
