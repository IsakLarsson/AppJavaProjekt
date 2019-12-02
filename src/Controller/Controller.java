package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import Model.Model;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

public class Controller {

    private ArrayList<BufferedImage> frames;

    public Controller(){

        SwingUtilities.invokeLater(() -> {
            frames = new ArrayList<>();
            Boolean gameIsRunning = true;
            GameWindow gameWindow = new GameWindow();
            GameFrame gameFrame = new GameFrame("Test", gameWindow);
            gameFrame.show();
        });



    }
}
