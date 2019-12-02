package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel {

    // Image frame
    private BufferedImage image;

    // Define constants for the game
    static final int CANVAS_WIDTH = 960;
    static final int CANVAS_HEIGHT = 640;

    private TileMap tileMap;
    // Handler for the custom drawing panel
    private GameCanvas canvas;

    // Constructor to initialize the UI components and game objects
    public GameWindow() {
        // UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);   // center of default BorderLayout
        //this.tileMap = tileMap;
    }

    public void setBufferedImage(BufferedImage bi) {
        this.image = bi;
    }

    // Refresh the display after each step.
    // Use (Graphics g) as argument if you are not using Java 2D.
    //Kanske ta in en buffered image och rita ut den
    public void drawGame(Graphics g) {
        g.drawImage(image,0,0, Color.black, null);
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
