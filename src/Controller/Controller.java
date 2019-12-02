package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import GUI.TileMap;
import Model.*;
import XML.ParseXML;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Controller {

    // Map with positions
    private TileMap tileMap;

    public Controller(){

        //
        SwingUtilities.invokeLater(() -> {

            // A panel that you draw on
            GameWindow gameWindow = new GameWindow();

            // A window frame containing a menubar
            GameFrame gameFrame = new GameFrame("Game", gameWindow);

            // Show the gui
            gameFrame.show();

            // An adapter that acts a middlehand between the gui and the model
            ModelAdapter adapter = new ModelAdapter(gameWindow);

            // A model
            Model model = new Model(adapter);

            // Update the gui with 1 seconds intervals
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // Update the gui
            gameWindow.update();

        });
    }
}
