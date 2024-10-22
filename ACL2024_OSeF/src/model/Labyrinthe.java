package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Labyrinthe {
    private File fichierLabyrinthe;
    private int niveau;
    private List<String> contenuLabyrinthe;

    public Labyrinthe(String cheminFichier, int niveau) {
        this.fichierLabyrinthe = new File(cheminFichier);
        this.niveau = niveau;
        chargerLabyrinthe();
    }

    private void chargerLabyrinthe() {
        try {
            contenuLabyrinthe = Files.readAllLines(fichierLabyrinthe.toPath());
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier : " + e.getMessage());
        }
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void afficherLabyrinthe() {
        if (contenuLabyrinthe != null) {
            for (String ligne : contenuLabyrinthe) {
                System.out.println(ligne);
            }
        } else {
            System.out.println("Labyrinthe non chargé.");
        }
    }
}
