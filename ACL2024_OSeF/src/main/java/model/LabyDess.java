package model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon ;
import javax.swing.JPanel;

public class LabyDess extends JPanel { 
    private int x, y ; // position en pixel
    //public Hero hero ;
    private Labyrinthe labyrinthe ;
    private int niv ;
    public  char[][] maze ; // labyrinthe actuel
    private int cellSize = 40 ; // Taille de chaque cellule en pixels

    public LabyDess(Labyrinthe obj) { //prend un tableau 2D de caractères (char[][]) représentant le labyrinthe
        //this.x = 0*cellSize ; this.y = 5*cellSize ; //La position de départ est un nombre entier de fois la taille d'une case, mais plus tard on peut se déplacer pixels âr pixels
        //this.hero = new Hero(x, y, 7) ;
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

    // Charger l'image "pierre.png"
    ImageIcon icoPierre = new ImageIcon(getClass().getResource("/images/pierre.png"));
    Image imagePierre = icoPierre.getImage();

    // Parcourir le labyrinthe et dessiner les murs
    for (int row = 0; row < maze.length; row++) {
        for (int col = 0; col < maze[row].length; col++) {
            if (maze[row][col] == '#') { // Cas des murs
                int x = col * cellSize;
                int y = row * cellSize;
                g.drawImage(imagePierre, x, y, cellSize, cellSize, this);
            }
        }
    }
}

}