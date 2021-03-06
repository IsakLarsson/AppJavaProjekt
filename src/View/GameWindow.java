package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * A window inside the game frame, comes with a drawing canvas that
 * updates when repaint() i called
 *
 * @version 1.0 19 December 2019
 * @author Joel Bystedt <id17jbt@cs.umu.se>
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 */

public class GameWindow extends JPanel {

    // Buttons
    private JButton button1;
    private JButton button2;

    // Tool bar
    JToolBar toolBar;

    // An image
    private BufferedImage image;

    // Constants for the game window
    static final int CANVAS_WIDTH = 600;
    static final int CANVAS_HEIGHT = 600;
    static final Dimension dimension = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);


    // Handler for the custom drawing panel
    private GameCanvas canvas;

    /**
     * Constructor for the window
     */
    public GameWindow() {

        // Set the layout
        setLayout(new BorderLayout());

        // Canvas to draw on
        canvas = new GameCanvas();
        canvas.setPreferredSize(dimension);
        add(canvas);

        // Tool bar
        add(buildToolBar(), BorderLayout.NORTH);
    }

    /**
     * Builds a tool bar
     * @return a tool bar
     */
    private JToolBar buildToolBar() {
        toolBar = new JToolBar();

        button1 = new JButton("Teleport");
        button2 = new JButton("Switch Path");

        toolBar.add(button1);
        toolBar.add(button2);

        return toolBar;
    }

    /**
     * Sets the image
     * @param bi a buffered image
     */
    public void setBufferedImage(BufferedImage bi) {
        this.image = bi;
    }

    /**
     * Draws a image on the canvas
     */
    public void drawGame(Graphics g) {
        g.drawImage(image,0,0, Color.BLACK,null);
    }

    /**
     * Repaints the panel
     */
    public void update() {
        repaint();
    }

    /**
     * Disables or enables the toolbar
     */
    public void setToolBarState(Boolean state) {
        button1.setEnabled(state);
        button2.setEnabled(state);
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

    /**
     * Custom drawing panel, written as an inner class.
     */
    class GameCanvas extends JPanel {

        /**
         * Constructor for the canvas
         */
        public GameCanvas() {
            setFocusable(true);
            requestFocus();
        }

        /**
         * Override paintComponent to do custom drawing.
         * Called back by repaint()
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGame(g);
        }
    }
}
