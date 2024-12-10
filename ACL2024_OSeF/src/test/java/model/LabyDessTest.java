package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabyDessTest {

    private Labyrinthe labyrinthe;
    private LabyDess labyDess;

    @BeforeEach
    void setup() {
        // Simuler un labyrinthe avec plusieurs niveaux
        List<List<String>> tousLesLaby = new ArrayList<>();
        tousLesLaby.add(Arrays.asList("niv0", "#####", "#...#", "#####"));
        tousLesLaby.add(Arrays.asList("niv1", "#####", "#.#.#", "#####"));
        tousLesLaby.add(Arrays.asList("niv2", "#####", "#...#", "#####"));
        tousLesLaby.add(Arrays.asList("niv3", "#####", "#.#.#", "#####"));

        labyrinthe = new Labyrinthe("path/to/fake/file");
        labyrinthe.TousLesLaby = tousLesLaby;

        labyDess = new LabyDess(labyrinthe);
    }

    @Test
    void testInitialNiveau() {
        // Vérifie que le niveau initial correspond à celui du labyrinthe
        assertEquals(0, labyDess.getNiveau(), "Le niveau initial doit être 0.");
    }

    @Test
    void testRemplirMaze() {
        // Charger un niveau spécifique
        labyDess.setNiveau(1);

        // Vérifier que le labyrinthe est correctement rempli
        char[][] maze = labyDess.getMaze();
        assertNotNull(maze, "Le tableau 'maze' ne doit pas être null.");
        assertEquals(3, maze.length, "Le labyrinthe doit avoir 3 lignes.");
        assertEquals(5, maze[0].length, "Le labyrinthe doit avoir 5 colonnes.");

        // Vérifier le contenu du labyrinthe
        assertArrayEquals(new char[]{'#', '#', '#', '#', '#'}, maze[0], "La première ligne doit être composée uniquement de murs.");
        assertArrayEquals(new char[]{'#', '.', '#', '.', '#'}, maze[1], "La deuxième ligne doit correspondre au niveau 1.");
    }

    @Test
    void testSetNiveauAndMazeUpdate() {
        // Charger un autre niveau et vérifier la mise à jour
        labyDess.setNiveau(2);
        assertEquals(2, labyDess.getNiveau(), "Le niveau doit être mis à jour à 2.");

        char[][] maze = labyDess.getMaze();
        assertNotNull(maze, "Le tableau 'maze' ne doit pas être null après mise à jour.");
        assertEquals('#', maze[0][0], "La première cellule doit contenir un mur.");
        assertEquals('.', maze[1][1], "La cellule [1][1] doit être vide ('.').");
    }

    @Test
    void testNiveauNonTrouve() {
        // Charger un niveau inexistant
        labyDess.setNiveau(99);

        // Vérifier que le tableau 'maze' reste null ou vide
        assertNull(labyDess.getMaze(), "Le tableau 'maze' doit être null si le niveau est introuvable.");
    }

    @Test
    void testTousLesLabyContientTousLesNiveaux() {
        // Vérifie que TousLesLaby contient bien les niveaux définis
        assertEquals(4, labyrinthe.TousLesLaby.size(), "Le nombre de niveaux dans 'TousLesLaby' doit être 4.");
        assertEquals("niv0", labyrinthe.TousLesLaby.get(0).get(0), "Le premier niveau doit être 'niv0'.");
        assertEquals("niv3", labyrinthe.TousLesLaby.get(3).get(0), "Le dernier niveau doit être 'niv3'.");
    }
}
