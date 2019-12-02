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

    private ArrayList<BufferedImage> frames;
    private TileMap tileMap;

    public Controller(){

        SwingUtilities.invokeLater(() -> {
            ParseXML xmlParser = new ParseXML();
            tileMap = xmlParser.parser();

            frames = new ArrayList<>();
            GameWindow gameWindow = new GameWindow(tileMap);
            GameFrame gameFrame = new GameFrame("Test", gameWindow);
            gameFrame.show();

            ModelAdapter adapter = new ModelAdapter(gameWindow);
            Model model = new Model(frames,adapter);

            gameWindow.update();

        });



    }
}
