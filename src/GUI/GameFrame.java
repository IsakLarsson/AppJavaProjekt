package GUI;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private JFrame frame;
    private GameWindow gameWindow;
    private int XSIZE = 1000;
    private int YSIZE = 600;

    public GameFrame(String title, GameWindow gameWindow){

        this.gameWindow = gameWindow;
        frame = new JFrame(title);

        // Set the content-pane of the JFrame to an instance of main JPanel
        frame.setContentPane(gameWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(buildMenuBar());

        //JPanel upperPanel = buildUpperPanel();
        //JPanel lowerPanel = buildlowerPanel();

        //frame.add(upperPanel, BorderLayout.NORTH);
        //frame.add(lowerPanel, BorderLayout.CENTER);
        frame.setSize(XSIZE, YSIZE);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Helpmethod for showing the gui
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Builds the upper panel
     *
     * @return The created panel
     */
    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();
        return upperPanel;
    }

    /**
     * Builds the lower panel
     *
     * @return The created panel
     */
    private JPanel buildlowerPanel() {
        JPanel lowerPanel = new JPanel();

        return lowerPanel;
    }

    /**
     * Build the menu bar
     * @return The menu bar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // Could add listeners here
        JMenuItem menuItem1 = new JMenuItem("New Game");
        JMenuItem menuItem2 = new JMenuItem("Restart level");
        JMenuItem menuItem3 = new JMenuItem("Pause");
        JMenuItem menuItem4 = new JMenuItem("Quit");

        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
        fileMenu.add(menuItem3);
        fileMenu.add(menuItem4);

        JMenu helpMenu = new JMenu("Help");

        // Could add listeners here
        JMenuItem menuItem5 = new JMenuItem("About");
        JMenuItem menuItem6 = new JMenuItem("Help");

        helpMenu.add(menuItem5);
        helpMenu.add(menuItem6);

        JButton button1 = new JButton("Farmer");
        JButton button2 = new JButton("Soldier");
        JButton button3 = new JButton("Teleporter");


        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(button1);
        menuBar.add(button2);
        menuBar.add(button3);


        return menuBar;
    }
}
