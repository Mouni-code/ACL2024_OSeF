package start;


import java.util.Scanner;

import engine.GameEngineGraphical;
import model.*;

import java.util.List ;//oui

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	/*public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");
		PacmanPainter painter = new PacmanPainter();
		PacmanController controller = new PacmanController();

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}*/
	//Test du jeu v.0

    public static void main(String[] args) {
        Hero hero = new Hero(0, 0);
        /*Labyrinthe obj = new Labyrinthe("C:\\Users\\gabk3\\OneDrive\\Documents\\Laby") ;
        obj.setNiveau(2) ;
        obj.afficherLabyrinthe() ;
        */
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Départ du héros en " + hero.getPosition()) ;

        while (true) {      //tu mets les directions une par une 
            System.out.println("Dans quel sens le héros doit se déplacer ? (UP, DOWN, LEFT, RIGHT) : ");
            String direction = scanner.nextLine();
            hero.move(direction);
            System.out.println("Position actuelle du héros : " + hero.getPosition());
        }
    }

}
