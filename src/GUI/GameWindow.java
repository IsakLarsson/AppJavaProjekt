package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JPanel {
    // Define constants for the game
    static final int CANVAS_WIDTH = 800;    // width and height of the drawing canvas
    static final int CANVAS_HEIGHT = 600;
    // ......

    // Define instance variables for the game objects
    // ......
    // ......

    // Handle for the custom drawing panel
    private GameCanvas canvas;

    //public static JMenuBar menuBar;    // the menu bar (if needed)

    // Constructor to initialize the UI components and game objects
    public GameWindow() {
        // Initialize the game objects
        gameInit();

        // UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        add(canvas);   // center of default BorderLayout

        // Other UI components such as button, score board, if any.
        // ......

        // Set up menu bar

    }

    // ------ All the game related codes here ------

    // Initialize all the game objects, run only once.
    public void gameInit() { }

    // Start and re-start the game.
    public void gameStart() { }

    // Shutdown the game, clean up code that runs only once.
    public void gameShutdown() { }

    // One step of the game.
    public void gameUpdate() { }

    // Refresh the display after each step.
    // Use (Graphics g) as argument if you are not using Java 2D.
    public void gameDraw(Graphics2D g2d) { }

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

    // Other methods
    // ......

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
            Graphics2D g2d = (Graphics2D)g;  // if using Java 2D
            super.paintComponent(g2d);       // paint background
            setBackground(Color.BLACK);      // may use an image for background

            // Draw the game objects
            gameDraw(g2d);
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
