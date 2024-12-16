package start;

import javax.swing.JFrame;

import engine.Chrono;
import model.Scene;
public class Main {

    public static Scene scene;

    public static void main(String[] args) {


        //Test du jeu v.0
            // Hero hero = new Hero(0, 0, 0,0,0,0,0,2,2,0);
            // Scanner scanner = new Scanner(System.in);
            // System.out.println("Les positions sont : "+hero.position.getX()+","+hero.position.getY());
    
            // while (true) {
            //     System.out.println("Dans quel sens le héros doit se déplacer ? (UP, DOWN, LEFT, RIGHT) : ");
            //     String direction = scanner.nextLine();
            //     hero.move(direction);
            //     System.out.println("Les positions sont : "+hero.position.getX()+","+hero.position.getY());
            // }
        

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

    //}
