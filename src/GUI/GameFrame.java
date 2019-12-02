package GUI;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private JFrame frame;
    private GameWindow gameWindow;
    private int XSIZE = 960;
    private int YSIZE = 640;

    public GameFrame(String title){
        gameWindow = new GameWindow();
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
        JMenu menu = new JMenu("File");

        // Could add listeners here
        JMenuItem menuItem1 = new JMenuItem("New Game");
        JMenuItem menuItem2 = new JMenuItem("Quit Game");

        menu.add(menuItem1);
        menu.add(menuItem2);

        JButton button = new JButton("Shop");

        menuBar.add(menu);
        menuBar.add(button);

        return menuBar;
    }
}
