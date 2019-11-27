package Controller;

import GUI.GameFrame;
import GUI.GameWindow;

public class Controller {

    public Controller(){
        GameWindow gameWindow = new GameWindow();
        GameFrame gameFrame = new GameFrame("Test", gameWindow);
        gameFrame.show();
        Thread thread = new Thread(() -> {
            for (int i=0; i < 150; i++) {
                gameWindow.update();
                gameWindow.incrementPositions();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();




    }
}
