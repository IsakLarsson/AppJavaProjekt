package GUI;

import Listeners.ButtonListener;
import Listeners.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameFrame {
    private JFrame frame;
    private ButtonListener buttonListener;
    private MenuListener menuListener;

    JMenuItem menuItem1;
    JMenuItem menuItem2;
    JMenuItem menuItem3;
    JMenuItem menuItem4;
    JMenuItem menuItem5;
    JMenuItem menuItem6;

    JButton button1;
    JButton button2;
    JButton button3;

    JLabel bank;

    GameWindow gameWindow;

    public GameFrame(String title, GameWindow gameWindow, ButtonListener buttonListener, MenuListener menuListener){

        frame = new JFrame(title);

        this.gameWindow = gameWindow;

        // Set the content-pane of the JFrame to an instance of main JPanel
        frame.setJMenuBar(buildMenuBar());
        frame.setContentPane(gameWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gameWindow.getDimension());
        frame.setLocationRelativeTo(null);
        frame.pack();

        this.menuListener = menuListener;
        this.buttonListener = buttonListener;
    }


    /**
     * Helpmethod for showing the gui
     */
    public void show() {
        frame.setVisible(true);

        BufferedImage image = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);

        image.getGraphics().setColor(Color.BLACK);
        image.getGraphics().setFont(new Font("TimesRoman", Font.PLAIN, 200));
        image.getGraphics().drawString("Press File -> New game, to start", 100, 150);

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

        bank = new JLabel("€: ");


        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(button1);
        menuBar.add(button2);
        menuBar.add(button3);
        menuBar.add(bank);


        return menuBar;
    }

    /**
     * Sets up the button listener for the "calculatePostitionQueue tests" button.
     *
     * @param buttonListener
     */
    public void setupListeners(ButtonListener buttonListener) {

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

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getLabel(){
        return bank;
    }
}
