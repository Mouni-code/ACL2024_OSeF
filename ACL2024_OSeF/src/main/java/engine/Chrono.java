package engine;
import start.Main;

public class Chrono implements Runnable {

    private final int pause = 3;


    @Override
    public void run() {
        while (true) {
            Main.scene.repaint();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
