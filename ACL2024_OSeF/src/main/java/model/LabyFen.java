package model;

import java.awt.Dimension;

import javax.swing.JFrame;

public class LabyFen {// pour tester à part. CETTE CLASSE NE SERT à RIEN.
    public static void main(String[] args) {
        JFrame frame = new JFrame("Labyrinthe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Créez le panneau du labyrinthe
        LabyDess labyDess = new LabyDess(null);
        
        // Définissez une taille fixe pour le panneau
        labyDess.setPreferredSize(new Dimension(400, 400)); // Par exemple, 400x400 pixels
        
        frame.add(labyDess);
        frame.pack(); // Ajuste la taille de la fenêtre au contenu
        frame.setResizable(true); // Empêche le redimensionnement de la fenêtre
        frame.setVisible(true);
    }
}
