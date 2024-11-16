package model;

import javax.swing.*;
import java.awt.*;
import java.util.List ;

public class LabyDess extends JPanel { 
    private int x, y ; // position en pixel
    public Hero hero ;
    private Labyrinthe labyrinthe ;
    private int niv ;
    private char[][] maze ; // labyrinthe actuel
    private int cellSize = 20 ; // Taille de chaque cellule en pixels

    public LabyDess(Labyrinthe obj) { //prend un tableau 2D de caractères (char[][]) représentant le labyrinthe
        this.x = 0*cellSize ; this.y = 5*cellSize ; //La position de départ est un nombre entier de fois la taille d'une case, mais plus tard on peut se déplacer pixels âr pixels
        this.hero = new Hero(x, y, 7) ;
        this.labyrinthe = obj ;
        this.niv = obj.getNiveau() ;
        remplirMaze() ;
    }

    //Cette partie lit dans quel laby de "Labyrinthe" on est et le reecrit en char dans "maze"
    public void remplirMaze(){
        for (int n = 0 ; n < labyrinthe.TousLesLaby.size() ; n++) {
            if ( labyrinthe.TousLesLaby.get(n).get(0).equals("niv" + getNiveau()) ) {
                List<String> niv_nLabyrinthe = labyrinthe.TousLesLaby.get(n) ;
                int hauteur = niv_nLabyrinthe.size() -1 ; // -1 car la première ligne est le nom du niveau
                int largeur = niv_nLabyrinthe.get(1).length() ; // On suppose que toutes les lignes ont la même longueur
        
                this.maze = new char[hauteur][largeur] ;
        
                for (int lgn = 1 ; lgn <= hauteur ; lgn++) {
                    String ligne = niv_nLabyrinthe.get(lgn) ;
                    for (int col = 0 ; col < ligne.length() ; col++) {
                        maze[lgn -1][col] = ligne.charAt(col) ;
                    }
                }
                break; // On a trouvé le bon labyrinthe, on peut sortir de la boucle
            }
        }
    }

    public char[][] getMaze(){
        return maze ;
    }

    public int getNiveau(){
        return niv ;
    }

    public void setNiveau(int nivo){
        this.niv = nivo ;
        remplirMaze() ;
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
        //--------------
        g.setColor(Color.RED); // Couleur du personnage
        g.fillOval(hero.getPosition().getX(), hero.getPosition().getY(), cellSize, cellSize) ;
        //--------------
    } 
}
