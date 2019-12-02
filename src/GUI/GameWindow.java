package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel {

    // Define constants for the game
    static final int CANVAS_WIDTH = 960;
    static final int CANVAS_HEIGHT = 640;

    private TileMap tileMap;
    // Handler for the custom drawing panel
    private GameCanvas canvas;

    // Constructor to initialize the UI components and game objects
    public GameWindow(TileMap tileMap) {
        // UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);   // center of default BorderLayout
        this.tileMap = tileMap;
    }

    public void setBufferedImage(BufferedImage bi) {
     //   this.image = bi;
    }

    // Refresh the display after each step.
    // Use (Graphics g) as argument if you are not using Java 2D.
    //Kanske ta in en buffered image och rita ut den
    public void drawGame(Graphics g) {
        for(int i = 0; i < tileMap.getMapData().length; i++){
            for(int j = 0; j < tileMap.getMapData().length; j++){
                Tile tile = tileMap.getTile(i,j);
                g.fillRect(tile.getxCoordinate()+(19*i),
                        tile.getyCoordinate()+(19*j),
                        tile.getxSize(),
                        tile.getySize());
                g.setColor(tile.getColor());
            }
        }

    }

    public void update() {
        repaint();
    }

    // Custom drawing panel, written as an inner class.
    class GameCanvas extends JPanel {
        // Constructor
        public GameCanvas() {
            setFocusable(true);  // so that this can receive key-events
            requestFocus();
        }

        // Override paintComponent to do custom drawing.
        // Called back by repaint().
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);       // paint background
            setBackground(Color.BLACK);      // may use an image for background

            // Draw the game objects
            drawGame(g);
        }
    }
}
