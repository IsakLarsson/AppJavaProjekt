package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.white;

public class GameWindow extends JPanel {
    // Define constants for the game
    static final int CANVAS_WIDTH = 800;
    static final int CANVAS_HEIGHT = 600;

    // Position x & y
    int x,y;

    // Handle for the custom drawing panel
    private GameCanvas canvas;

    // Constructor to initialize the UI components and game objects
    public GameWindow() {
        
        // UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);   // center of default BorderLayout
    }

    public void incrementPositions() {
        this.x+=1;
        this.y+=1;
    }

    // Refresh the display after each step.
    // Use (Graphics g) as argument if you are not using Java 2D.
    public void gameDraw(Graphics g) {
        g.setColor(white);
        g.fill3DRect(x , y ,
                15, 15, true);
    }

    public void update() {
        repaint();
    }

    // Process a key-pressed event.
    public void gameKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                // ......
                break;
            case KeyEvent.VK_DOWN:
                // ......
                break;
            case KeyEvent.VK_LEFT:
                // ......
                break;
            case KeyEvent.VK_RIGHT:
                // ......
                break;
        }
    }

    // Custom drawing panel, written as an inner class.
    class GameCanvas extends JPanel implements KeyListener {
        // Constructor
        public GameCanvas() {
            setFocusable(true);  // so that this can receive key-events
            requestFocus();
            addKeyListener(this);
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

        // KeyEvent handlers
        @Override
        public void keyPressed(KeyEvent e) {
            gameKeyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) { }  // not used

        @Override
        public void keyTyped(KeyEvent e) { }     // not used
    }

}
