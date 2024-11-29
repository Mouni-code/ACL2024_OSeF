// package engine;

// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;

// import start.Main;

// public class Keyboard implements KeyListener{

//     @Override
//     public void keyTyped(KeyEvent e) {}

//     @Override
//     public void keyPressed(KeyEvent e) {
//         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//             Main.scene.setDx(1);
//         }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//             Main.scene.setDx(-1);
//         }else if (e.getKeyCode() == KeyEvent.VK_UP){
//             Main.scene.setDy(-1);
//         }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
//             Main.scene.setDy(1);
//         }
//     }

//     @Override
//     public void keyReleased(KeyEvent e) {
//         Main.scene.setDx(0);
//         Main.scene.setDy(0);
//     }
    
// }
