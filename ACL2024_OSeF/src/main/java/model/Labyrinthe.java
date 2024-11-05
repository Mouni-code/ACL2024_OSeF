package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path ;
import java.nio.file.Paths ;
import java.util.List;
import java.util.ArrayList ;

public class Labyrinthe {
    //private File fichierLabyrinthe; //les fichiers des lab
    private int niveau;
    public List<List<String>> TousLesLaby = new ArrayList<>() ; //contenu de tous les labyrinthes selon les niveaux
    Path dossier ;

    public Labyrinthe(String cheminDossier) { 
        //this.fichierLabyrinthe = new File(cheminFichier);
        this.niveau = 0 ; // toujours 0 au début ? Why not logique
        chargerLabyrinthe(cheminDossier);
    }

    private void chargerLabyrinthe(String cheminDossier) {
        dossier = Paths.get(cheminDossier) ;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dossier)){ //for in range les niv
            for ( Path fichier : stream ){
                if( Files.isRegularFile(fichier) ){
                    List<String> contenuLabyrinthe = Files.readAllLines(fichier) ;
                    TousLesLaby.add(contenuLabyrinthe) ;
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier : " + e.getMessage());
        }
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
        //on change
    }

    public void afficherLabyrinthe() {

        for( int i = 0 ; i < TousLesLaby.size() ; i++ ){
            if( TousLesLaby.get(i).get(0).equals("niv" + niveau) ){
                for( int j = 1 ; j < TousLesLaby.get(i).size() ; j++ ){
                    System.out.println(TousLesLaby.get(i).get(j)) ;
                }
                return ;
            }
        /*if (contenuLabyrinthe != null) {
            for (String ligne : contenuLabyrinthe) {
                System.out.println(ligne);
            }
        } else {
            System.out.println("Labyrinthe non chargé.");
        }*/
    }
}
}
