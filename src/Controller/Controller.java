package Controller;

import GUI.GameFrame;
import GUI.GameWindow;

import java.util.ArrayList;
import java.util.Stack;

public class Controller {

    public Controller(){
        GameWindow gameWindow = new GameWindow();
        GameFrame gameFrame = new GameFrame("Test", gameWindow);
        gameFrame.show();

        // Skapa en tråd för att uppdatera GUI
        Thread thread = new Thread(() -> {
            //15 sekunder
            for (int i=0; i < 150; i++) {
                ArrayList<Integer> a = new ArrayList<>();
                a.add(i);
                a.add(i);
                a.add(10);
                a.add(10);

                gameWindow.setPositions(a);    //Uppdatera positions
                gameWindow.update();                //Uppdatera window

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
