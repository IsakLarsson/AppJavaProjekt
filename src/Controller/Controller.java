package Controller;

import GUI.GameFrame;
import GUI.GameWindow;

public class Controller {

    public Controller(){
        GameWindow gameWindow = new GameWindow();
        GameFrame gameFrame = new GameFrame("Test", gameWindow);





        gameFrame.show();
    }
}
