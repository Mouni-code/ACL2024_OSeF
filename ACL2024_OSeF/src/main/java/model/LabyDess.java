package model;

import java.util.List;

import javax.swing.JPanel;

public final class LabyDess extends JPanel { 
    private final Labyrinthe labyrinthe ;
    private int niv ;
    public  char[][] maze ; // labyrinthe actuel
    public LabyDess(Labyrinthe obj) { //prend un tableau 2D de caractères (char[][]) représentant le labyrinthe
        //this.x = 0*cellSize ; this.y = 5*cellSize ; //La position de départ est un nombre entier de fois la taille d'une case, mais plus tard on peut se déplacer pixels âr pixels
        //this.hero = new Hero(x, y, 7) ;
        this.labyrinthe = obj ;
        this.niv = obj.getNiveau() ;
        remplirMaze() ;
    }
    public static void main(String[] args) {
        Labyrinthe labyrinthe = new Labyrinthe("ACL2024_OSeF/src/main/java/model/Laby");
        LabyDess labydess = new LabyDess(labyrinthe);
        int lvl = labydess.getNiveau();
        System.out.println("Niveau actuel : " + lvl);

        for(int n = 0; n< Labyrinthe.TousLesLaby.size(); n++){
            System.out.println("Index " + n + ": " + Labyrinthe.TousLesLaby.get(n));
        }

    }
    
    //Cette partie lit dans quel laby de "Labyrinthe" on est et le reecrit en char dans "maze"
    public void remplirMaze() {
        //System.out.println("Chargement du niveau : " + getNiveau());
        boolean niveauTrouve = false;
    
        // Parcours de tous les labyrinthes
        for (int n = 0; n < Labyrinthe.TousLesLaby.size(); n++) {
            // Récupération de l'identifiant du niveau courant et nettoyage avec trim()
            String niveauActuel = Labyrinthe.TousLesLaby.get(n).get(0).trim();
            //System.out.println("Vérification niveau dans TousLesLaby : " + niveauActuel + " (Attendu : niv" + getNiveau() + ")");
    
            // Vérification de la correspondance avec le niveau attendu
            if (niveauActuel.equals("niv" + getNiveau())) {
                niveauTrouve = true;
                //System.out.println("Correspondance trouvée pour : " + niveauActuel);
    
                // Extraction des données du labyrinthe pour ce niveau
                List<String> niv_nLabyrinthe = Labyrinthe.TousLesLaby.get(n);
                int hauteur = niv_nLabyrinthe.size() - 1; // -1 car la première ligne est le nom du niveau
                int largeur = niv_nLabyrinthe.get(1).length();
    
                this.maze = new char[hauteur][largeur];
    
                // Remplissage du tableau `maze`
                for (int lgn = 1; lgn <= hauteur; lgn++) {
                    String ligne = niv_nLabyrinthe.get(lgn);
                    for (int col = 0; col < ligne.length(); col++) {
                        maze[lgn - 1][col] = ligne.charAt(col);
                    }
                }
                break; // On arrête la recherche après avoir trouvé le niveau
            }
        }
    
        // Si aucun niveau n'a été trouvé
        if (!niveauTrouve) {
            System.err.println("Erreur : Niveau non trouvé !");
            System.err.println("Niveaux disponibles dans TousLesLaby :");
            for (List<String> niveau : labyrinthe.TousLesLaby) {
                System.err.println("- " + niveau.get(0).trim());
            }
            this.maze = null; // Mettre maze à null si le niveau n'est pas trouvé
        }
    }
    
    public char[][] getMaze() {
        return maze;
    }
    
    public void setNiveau(int nivo) {
        this.niv = nivo;
        remplirMaze();
    }
    public int getNiveau(){
        return niv ;
    }



}