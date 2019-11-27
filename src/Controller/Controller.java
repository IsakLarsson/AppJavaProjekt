package Controller;

import GUI.GameFrame;
import GUI.GameWindow;

public class Controller {

    public Controller(){
        GameWindow gameWindow = new GameWindow();
        GameFrame gameFrame = new GameFrame("Test", gameWindow);
        gameFrame.show();

        // Skapa en tråd för att uppdatera GUI
        Thread thread = new Thread(() -> {
            //15 sekunder
            for (int i=0; i < 15; i++) {
                gameWindow.update();                //Uppdatera window
                gameWindow.incrementPositions();    //Uppdatera positions
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();




    }
}
