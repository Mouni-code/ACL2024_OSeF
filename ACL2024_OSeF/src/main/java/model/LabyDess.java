package model;

import javax.swing.*;
import java.awt.*;
import java.util.List ;

public class LabyDess extends JPanel { 
    private Labyrinthe obj ;
    private int niv ;
    private char[][] maze ; // labyrinthe actuel
    private int cellSize = 25 ; // Taille de chaque cellule en pixels

    public LabyDess(Labyrinthe obj) { //prend un tableau 2D de caractères (char[][]) représentant le labyrinthe
        this.obj = obj ;
        this.niv = obj.getNiveau() ;
        remplirMaze() ;
    }
        /*
         *Cette partie lit dans quel laby de "Labyrinthe" on est et le reecrit en char dans "maze"
         * On peut en faire une classe
        */

    public char[][] remplirMaze(){
        for (int n = 0 ; n < this.obj.TousLesLaby.size() ; n++) {
            if (this.obj.TousLesLaby.get(n).get(0).equals("niv" + getNiveau())) {
                List<String> labyrinthe = this.obj.TousLesLaby.get(n) ;
                int hauteur = labyrinthe.size() -1 ; // -1 car la première ligne est le nom du niveau
                int largeur = labyrinthe.get(1).length() ; // On suppose que toutes les lignes ont la même longueur
        
                this.maze = new char[hauteur][largeur] ;
        
                for (int lgn = 1 ; lgn < labyrinthe.size() ; lgn++) {
                    String ligne = labyrinthe.get(lgn) ;
                    for (int col = 0 ; col < ligne.length() ; col++) {
                        maze[lgn -1][col] = ligne.charAt(col) ;
                    }
                }
                break; // On a trouvé le bon labyrinthe, on peut sortir de la boucle
            }
        }
        return maze ;
    }

    public int getNiveau(){
        return niv ;
    }

    public void setNiveau(int nivo){
        this.niv = nivo ;
        this.maze = remplirMaze() ;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                switch (maze[row][col]) {
                    case '#': // Mur
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, cellSize, cellSize);
                        break;
                    case 'h':
                        g.setColor(Color.GREEN);
                        g.fillRect(x, y, cellSize, cellSize);
                        break;
                    case ' ': // Chemin
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, cellSize, cellSize);
                        break;
                    case 'H': // Healing pad
                        g.setColor(Color.GREEN);
                        g.fillOval(x, y, cellSize, cellSize);
                        break;
                    case 'T': // Piege
                        g.setColor(Color.RED);
                        g.fillOval(x, y, cellSize, cellSize);
                        break;
                    case 'M' : //Marchand
                        g.setColor(Color.ORANGE);
                        g.fillOval(x, y, cellSize, cellSize);
                        break;
                    case 'S' : //Spawner
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillOval(x, y, cellSize, cellSize);
                        break;
                    case 'L' :
                        g.setColor(Color.BLUE);
                        g.fillOval(x, y, cellSize, cellSize);
                        break;
                    // Ajouter d'autres cases...
                }
            }
        }
    }
}
