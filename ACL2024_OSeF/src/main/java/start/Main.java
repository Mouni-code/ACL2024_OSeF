package start;

import java.awt.Dimension;

import javax.swing.* ;

import model.LabyDess;
import model.Labyrinthe ;
import model.MovingCharacter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Test du jeu v.0-------------------------------------------------------------------------------------------
   /*  public static void main(String[] args) {
        Hero hero = new Hero(0, 0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Départ du héros en " + hero.getPosition()) ;

        while (true) {      //tu mets les directions une par une 
            System.out.println("Dans quel sens le héros doit se déplacer ? (UP, DOWN, LEFT, RIGHT) : ");
            String direction = scanner.nextLine();
            hero.move(direction);
            System.out.println("Position actuelle du héros : " + hero.getPosition());
        }*/


    //Test l'aff des laby.--------------------------------------------------------------------------------------
    /*public static void main(String[] args) {
        Labyrinthe obj = new Labyrinthe("C:\\Users\\gabk3\\OneDrive\\Documents\\Laby") ;
        obj.setNiveau(2) ;
        obj.afficherLabyrinthe() ;
        */

    
    //Test Aff Graphique----------------------------------------------------------------------------------------
    /*public static void main(String[] args) {

        // Créez le panneau du labyrinthe
        LabyDess labyDess = new LabyDess(new Labyrinthe("ACL2024_OSeF\\ACL2024_OSeF\\src\\main\\java\\model\\Laby")) ;
        labyDess.setNiveau(0);
        labyDess.setPreferredSize(new Dimension(1920, 1080)); // Taille panneau 1920x1080 pixels
        
        JFrame frame = new JFrame("nom du jeu") ;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;

        frame.add(labyDess) ;
        frame.pack() ; // Ajuste la taille de la fenêtre au contenu
        frame.setResizable(true) ; // Permet le redimensionnement de la fenêtre
        frame.setVisible(true) ;
    }*/

    public static void main(String[] arg){
        new MovingCharacter() ;
    }
}