package start;


import java.util.Scanner;

import engine.GameEngineGraphical;
import model.*;

import java.util.List ;
import javax.swing.*;
import java.awt.*;

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
	//Test du jeu v.0--------------------------------------------------------------------------------------

    /*public static void main(String[] args) {
        Labyrinthe obj = new Labyrinthe("C:\\Users\\gabk3\\OneDrive\\Documents\\Laby") ;
        obj.setNiveau(2) ;
        obj.afficherLabyrinthe() ;
        */
    //Test l'aff des laby.--------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Labyrinthe obj = new Labyrinthe("C:\\Users\\gabk3\\OneDrive\\Documents\\Laby") ;
        obj.setNiveau(2) ;
        obj.afficherLabyrinthe() ;

        char[][] maze ;
        for( int n = 0 ; n < obj.TousLesLaby.size() ; n++ ){
            if( obj.TousLesLaby.get(n).get(0).equals("niv" + obj.getNiveau()) ){
                for( int lgn = 1 ; lgn < obj.TousLesLaby.get(n).size() ; lgn++ ){
                    char[] var = obj.TousLesLaby.get(n).get(lgn).toCharArray() ; 
                    maze = new char[obj.TousLesLaby.get(n).size()][var.length];
                    for( int k = 0 ; k < var.length ; k++ ){
                        maze[lgn][k] = var[k] ;
                    }
                }
            }
        }

        
        for (int n = 0; n < obj.TousLesLaby.size(); n++) {
        if (obj.TousLesLaby.get(n).get(0).equals("niv" + obj.getNiveau())) {
            List<String> labyrinthe = obj.TousLesLaby.get(n);
            int hauteur = labyrinthe.size() - 1; // -1 car la première ligne est le nom du niveau
            int largeur = labyrinthe.get(1).length(); // On suppose que toutes les lignes ont la même longueur
        
            maze = new char[hauteur][largeur];
        
            for (int lgn = 1; lgn < labyrinthe.size() ; lgn++) {
                String ligne = labyrinthe.get(lgn);
                for (int col = 0; col < ligne.length() ; col++) {
                    maze[lgn - 1][col] = ligne.charAt(col);
                }
            }

        break; // On a trouvé le bon labyrinthe, on peut sortir de la boucle
    }
}

        JFrame frame = new JFrame("Labyrinthe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Créez le panneau du labyrinthe
        LabyDess labyDess = new LabyDess(maze);
        
        // Définissez une taille fixe pour le panneau
        labyDess.setPreferredSize(new Dimension(1400, 500)); // Par exemple, 400x400 pixels
        
        frame.add(labyDess);
        frame.pack(); // Ajuste la taille de la fenêtre au contenu
        frame.setResizable(true); // Empêche le redimensionnement de la fenêtre
        frame.setVisible(true);

    }


        
        /*public static...
        Hero hero = new Hero(0, 0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Départ du héros en " + hero.getPosition()) ;

        while (true) {      //tu mets les directions une par une 
            System.out.println("Dans quel sens le héros doit se déplacer ? (UP, DOWN, LEFT, RIGHT) : ");
            String direction = scanner.nextLine();
            hero.move(direction);
            System.out.println("Position actuelle du héros : " + hero.getPosition());
        }*/
}
