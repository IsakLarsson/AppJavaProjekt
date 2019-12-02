package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import Model.*;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Controller {

    private ArrayList<BufferedImage> frames;

    public Controller(){

        SwingUtilities.invokeLater(() -> {
            frames = new ArrayList<>();
            GameWindow gameWindow = new GameWindow();
            GameFrame gameFrame = new GameFrame("Test", gameWindow);
            gameFrame.show();

            ModelAdapter adapter = new ModelAdapter(gameWindow);
            Model model = new Model(frames,adapter);
        

        });



    }
}
