package GUI;

import Listeners.ButtonListener;
import Listeners.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A window frame for the game
 */
public class GameFrame {
    private JFrame frame;
    private ButtonListener buttonListener;
    private MenuListener menuListener;

    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;

    private JButton button1;
    private JButton button2;
    private JButton button3;

    private JLabel cost1;
    private JLabel cost2;
    private JLabel cost3;

    private JLabel bank;
    private JLabel timer;

    private GameWindow gameWindow;

    private ArrayList<String> levels;

    /**
     * Creates a frame for the game and its menus
     * @param title Title of the game
     * @param gameWindow the window inside the frame
     * @param buttonListener listener for the buttons
     * @param menuListener listener for the menu items
     * @param levels list of the levels
     */
    public GameFrame(String title, GameWindow gameWindow, ButtonListener buttonListener,
                     MenuListener menuListener, ArrayList<String> levels){

        frame = new JFrame(title);

        this.gameWindow = gameWindow;
        this.levels = levels;

        frame.setJMenuBar(buildMenuBar());
        frame.setContentPane(gameWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gameWindow.getDimension());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();

        this.menuListener = menuListener;
        this.buttonListener = buttonListener;
    }


    /**
     * Showing the gui start view
     */
    public void show() {
        frame.setVisible(true);

        BufferedImage image = new BufferedImage(800, 800,
                BufferedImage.TYPE_INT_ARGB);

        image.getGraphics().setColor(Color.BLACK);
        image.getGraphics().setFont(new Font("TimesRoman", Font.PLAIN, 200));
        image.getGraphics().drawString("Press File -> New game, to start",
                200, 300);

        GameWindow window = (GameWindow) frame.getContentPane();

        window.setBufferedImage(image);
        window.update();
    }

    /**
     * Build the menu bar
     * @return The menu bar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");


        menuItem1 = new JMenuItem("New Game");
        menuItem2 = new JMenuItem("Restart level");
        menuItem3 = new JMenuItem("Pause");
        menuItem4 = new JMenuItem("Quit");

        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
        fileMenu.add(menuItem3);
        fileMenu.add(menuItem4);

        JMenu helpMenu = new JMenu("Help");

        menuItem5 = new JMenuItem("About");
        menuItem6 = new JMenuItem("Help");

        helpMenu.add(menuItem5);
        helpMenu.add(menuItem6);

        button1 = new JButton("Farmer");
        button2 = new JButton("Soldier");
        button3 = new JButton("Teleporter");

        cost1 = new JLabel("$5");
        cost2 = new JLabel("$10");
        cost3 = new JLabel("$20");
        bank = new JLabel("€: ");
        timer = new JLabel(" Time: 00");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(button1);
        menuBar.add(cost1);
        menuBar.add(button2);
        menuBar.add(cost2);
        menuBar.add(button3);
        menuBar.add(cost3);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(bank);
        menuBar.add(timer);

        return menuBar;
    }

    /**
     * Sets up the listener for the buttons.
     */
    public void setupListeners() {

        //
        menuItem1.addActionListener(this.menuListener);
        menuItem2.addActionListener(this.menuListener);
        menuItem3.addActionListener(this.menuListener);
        menuItem4.addActionListener(this.menuListener);

        //
        menuItem5.addActionListener(this.menuListener);
        menuItem6.addActionListener(this.menuListener);

        //
        button1.addActionListener(this.buttonListener);
        button2.addActionListener(this.buttonListener);
        button3.addActionListener(this.buttonListener);

        //
        gameWindow.getButton1().addActionListener(this.buttonListener);
        gameWindow.getButton2().addActionListener(this.buttonListener);
    }

    public void setButtonState(Boolean state) {
        button1.setEnabled(state);
        button2.setEnabled(state);
        button3.setEnabled(state);
        menuItem2.setEnabled(state);
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return the bank label
     */
    public JLabel getBank(){
        return bank;
    }

    /**
     * @return the timer label
     */
    public JLabel getTimer(){
        return timer;
    }

    /**
     * @return the game window
     */
    public GameWindow getGameWindow() {
        return gameWindow;
    }

    /**
     * @return the new menu menuitem
     */
    public JMenuItem getNewGame() {
        return menuItem1;
    }

    /**
     * @return the pause menu menuitem
     */
    public JMenuItem getPause() {
        return menuItem3;
    }
}
