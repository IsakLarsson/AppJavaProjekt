package Model;

import GUI.GameWindow;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends Thread{
    ModelAdapter modelAdapter;
    Level level;

    public Game(GameWindow gui){
        XMLParser parser = new XMLParser();
        level = new Level();
        modelAdapter = new ModelAdapter(gui);

    }

    @Override
    public void run() {
        drawGame();
    }

    public void drawGame(){
        BufferedImage gameImage = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);

        Graphics g = gameImage.getGraphics();
        Level map = new Level();

        for (int i = 0; i < level.getMapSize(); i++) {
            for (int j = 0; j < level.getMapSize(); j++){

                Tile tile = level.getTile(i,j);
                g.setColor(tile.getColor());
                g.fillRect(tile.getxCoordinate(), tile.getyCoordinate(),
                        tile.getSize(),
                        tile.getSize());
            }
        }
    }

}
