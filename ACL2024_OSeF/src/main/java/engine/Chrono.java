package engine;
import start.Main;
public class Chrono implements Runnable {

    private final int pause = 3;//temps d'attente entre 2 tours de boucles

    @Override
    public void run() {
       
        while (true) { 
            
            Main.scene.repaint();
            
            try {
                Thread.sleep(pause);//flux
            } catch (InterruptedException e) {} 
        }
    }
}
    

