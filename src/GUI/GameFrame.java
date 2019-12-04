package GUI;

import Listeners.ButtonListener;
import Listeners.MenuListener;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    private JFrame frame;
    private ButtonListener buttonListener;
    private MenuListener menuListener;
    private int XSIZE = 1000;
    private int YSIZE = 600;

    JMenuItem menuItem1;
    JMenuItem menuItem2;
    JMenuItem menuItem3;
    JMenuItem menuItem4;
    JMenuItem menuItem5;
    JMenuItem menuItem6;

    JButton button1;
    JButton button2;
    JButton button3;


    public GameFrame(String title, GameWindow gameWindow, ButtonListener buttonListener, MenuListener menuListener){

        frame = new JFrame(title);

        // Set the content-pane of the JFrame to an instance of main JPanel
        frame.setContentPane(gameWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(buildMenuBar());
        frame.setSize(XSIZE, YSIZE);
        frame.setLocationRelativeTo(null);

        this.menuListener = menuListener;
        this.buttonListener = buttonListener;
    }

    /**
     * Helpmethod for showing the gui
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Build the menu bar
     * @return The menu bar
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // Could add listeners here
        menuItem1 = new JMenuItem("New Game");
        menuItem2 = new JMenuItem("Restart level");
        menuItem3 = new JMenuItem("Pause");
        menuItem4 = new JMenuItem("Quit");

        fileMenu.add(menuItem1);
        fileMenu.add(menuItem2);
        fileMenu.add(menuItem3);
        fileMenu.add(menuItem4);

        JMenu helpMenu = new JMenu("Help");

        // Could add listeners here
        menuItem5 = new JMenuItem("About");
        menuItem6 = new JMenuItem("Help");

        helpMenu.add(menuItem5);
        helpMenu.add(menuItem6);

        button1 = new JButton("Farmer");
        button2 = new JButton("Soldier");
        button3 = new JButton("Teleporter");



        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(button1);
        menuBar.add(button2);
        menuBar.add(button3);


        return menuBar;
    }

    /**
     * Sets up the button listener for the "run tests" button.
     *
     * @param buttonListener
     */
    public void setupListeners(ButtonListener buttonListener) {

        //
        menuItem5.addActionListener(this.menuListener);
        menuItem6.addActionListener(this.menuListener);

        button1.addActionListener(this.buttonListener);
        button2.addActionListener(this.buttonListener);
        button3.addActionListener(this.buttonListener);
    }

    public JFrame getFrame() {
        return frame;
    }
}
