package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel {

    // Buttons
    private JButton button1;
    private JButton button2;

    // Model.Frame frame
    private BufferedImage image;

    // Define constants for the game
    static final int CANVAS_WIDTH = 400;
    static final int CANVAS_HEIGHT = 400;
    static final Dimension dimension = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);


    // Handler for the custom drawing panel
    private GameCanvas canvas;

    // Constructor to initialize the UI components and game objects
    public GameWindow() {

        //
        setLayout(new BorderLayout());

        // UI components
        canvas = new GameCanvas();
        canvas.setPreferredSize(dimension);
        add(canvas);   // center of default BorderLayout

        // Tool bar
        add(buildToolBar(), BorderLayout.NORTH);
    }

    private JToolBar buildToolBar() {
        JToolBar toolBar = new JToolBar();

        button1 = new JButton("Teleport");
        button2 = new JButton("Switch Path");


        toolBar.add(button1);
        toolBar.add(button2);

        return toolBar;
    }

    public void setBufferedImage(BufferedImage bi) {
        this.image = bi;
    }

    // Refresh the display after each step.
    // Use (Graphics g) as argument if you are not using Java 2D.
    //Kanske ta in en buffered image och rita ut den
    public void drawGame(Graphics g) {
        g.drawImage(image,0,0, Color.BLACK,null);
    }

    public void update() {
        repaint();
    }

    public static Dimension getDimension() {
        return new Dimension(CANVAS_WIDTH + 14,
                CANVAS_HEIGHT);
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
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
            // Draw the game objects
            drawGame(g);
        }
    }
}
