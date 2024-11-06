package model;

import javax.swing.*;
import java.awt.*;

public class LabyDess extends JPanel { 
    private char[][] maze ; // labyrinthe actuel
    private int cellSize = 25 ; // Taille de chaque cellule en pixels

    public LabyDess(char[][] maze) { //prend un tableau 2D de caractères (char[][]) représentant le labyrinthe
        this.maze = maze ;
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
