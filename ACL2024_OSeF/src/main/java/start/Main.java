package start;

import model.*;
import java.util.Scanner;
import engine.GameEngineGraphical;

import java.util.List ;
import javax.swing.*;
import java.awt.*;

public class Main {


	//Test du jeu v.0-------------------------------------------------------------------------------------------
	/*public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter();
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}*/


    //Test l'aff des laby.--------------------------------------------------------------------------------------
    /*public static void main(String[] args) {
        Labyrinthe obj = new Labyrinthe("C:\\Users\\gabk3\\OneDrive\\Documents\\Laby") ;
        obj.setNiveau(2) ;
        obj.afficherLabyrinthe() ;
        */

    
    //Test Aff Graphique----------------------------------------------------------------------------------------
    public static void main(String[] args) {

        JFrame frame = new JFrame("nom du jeu") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        
        // Créez le panneau du labyrinthe
        LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF\\src\\main\\java\\model\\Laby")) ;
        labyDess.setNiveau(1);
        
        labyDess.setPreferredSize(new Dimension(1920, 1080)); // Taille panneau 1920x1080 pixels
        
        frame.add(labyDess) ;
        frame.pack() ; // Ajuste la taille de la fenêtre au contenu
        frame.setResizable(true) ; // Permet le redimensionnement de la fenêtre
        frame.setVisible(true) ;
    }
}
