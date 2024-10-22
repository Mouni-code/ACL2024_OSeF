package start;

import model.PacmanPainter;

import java.util.Scanner;

import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;
import model.Hero;
import model.Position;

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

    public static void main(String[] args) {
        Hero hero = new Hero(0, 0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Départ du héros en " + hero.getPosition());

        while (true) {
            System.out.println("Dans quel sens le héros doit se déplacer ? (UP, DOWN, LEFT, RIGHT) : ");
            String direction = scanner.nextLine();
            hero.move(direction);
            System.out.println("Position actuelle du héros : " + hero.getPosition());
        }
    }

}
