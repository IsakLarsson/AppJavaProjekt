package GUI;

import Unit.Farmer;
import Unit.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import static java.awt.Color.white;

public class GameWindow extends JPanel {

    // An image
    BufferedImage image;

    // Define constants for the game
    static final int CANVAS_WIDTH = 800;
    static final int CANVAS_HEIGHT = 600;

    // Position x & y
    int farmerPosX, farmerPosY, towerPosX, towerPosY;

    // Units that needs to be updated
    Farmer farmer;
    Tower tower;

    // Handle for the custom drawing panel
    private GameCanvas canvas;

    // Constructor to initialize the UI components and game objects
    public GameWindow() {
        
        // UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);   // center of default BorderLayout

        // Init units
        farmer = new Farmer();
        tower = new Tower();
    }

    public void setPositions(ArrayList al) {
        this.farmerPosX = (int) al.get(0);
        this.farmerPosY = (int) al.get(1);
        this.towerPosX = (int) al.get(2);
        this.towerPosY = (int) al.get(3);



    }

    // Refresh the display after each step.
    // Use (Graphics g) as argument if you are not using Java 2D.
    public void gameDraw(Graphics g) {

        // Draw a new image in the canvas
        g.drawImage(image,0,0,this);
        // Update units
        farmer.draw(g, this.farmerPosX, this.farmerPosY);
        tower.draw(g, this.towerPosX, this.towerPosY);
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
            gameDraw(g);
        }
    }
}
