import gui.FrameJCaroGame;

import javax.swing.*;

public class Game {
    public static  void main(String args[]) {
        System.out.println("Caro game make by nnthienbao040@gmail.com");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameJCaroGame();
            }
        });
    }
}
