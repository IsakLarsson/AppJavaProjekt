package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import Listeners.ButtonListener;
import Listeners.MenuListener;
import Model.*;
import Model.Unit.Farmer;
import Model.Unit.Soldier;
import Model.Unit.Teleporter;
import Model.XML.XMLParser;


import javax.swing.*;
import java.util.ArrayList;

/**
 * A controller that controls the model and view
 */
public class Controller {

    // The frame of the game
    private GameFrame gameFrame;

    // The window inside the frame
    private GameWindow gameWindow;

    // The listeners
    private MenuListener menuListener;
    private ButtonListener buttonListener;

    // Adapter between the model and view
    private ModelAdapter adapter;

    // Interval for which the game should update
    private int updateInterval = 50;

    // A game object
    private Game game;

    //
    private Object lock;
    private Object startObject;

    // Path to the xml
    String filePath;

    /**
     * The controller creates two threads, one for the gui,
     * and one for the game logic
     * @param filePath is the path to the xml file
     */
    public Controller(String filePath){
        lock = new Object();
        startObject = new Object();

        this.filePath = filePath;
        XMLParser parser = new XMLParser(filePath);
        parser.parseXML();

        SwingUtilities.invokeLater(() -> {

            // Create action listeners
            buttonListener = new ButtonListener(this);
            menuListener = new MenuListener(this);

            // Create a new game window and put it in the frame
            gameWindow = new GameWindow();
            gameFrame = new GameFrame("Game", gameWindow, buttonListener,
                    menuListener, parser.getLevels());

            // Set up the listeners
            gameFrame.setupListeners();

            // Show the gui
            gameFrame.show();

            synchronized (lock){
                lock.notify();
            }
        });

        synchronized (lock){
            try {
                lock.wait();
                adapter = new ModelAdapter(gameFrame, parser.getLevels());
                game = new Game(adapter, updateInterval, filePath);
                game.start();
            } catch (InterruptedException e) {
                //Skriv lämpligt fel
            }
        }
    }

    /**
     * Opens a dialog
     * @param title title of the dialog
     * @param text text in the dialog
     */
    public void openDialog(String title, String text) {
        JOptionPane.showMessageDialog(gameFrame.getFrame(),text,title,JOptionPane.PLAIN_MESSAGE);
    }

    public void teleportUnit() {

    }

    /**
     * Makes units choose the other path available
     */
    public void splitPath() {
        game.changePath();
    }

    /**
     * Spawn a unit
     * @param s The name of the unit
     */
    public void spawnUnit (String s) {
        int bank;
        switch (s) {
            case "Farmer":
                Farmer farmer = new Farmer();
                bank = game.spawn(farmer);
                gameFrame.getBank().setText("€ " + bank);
                break;
            case "Soldier":
                Soldier soldier = new Soldier();
                bank = game.spawn(soldier);
                gameFrame.getBank().setText("€ " + bank);
                break;
            case "Teleporter":
                Teleporter teleporter = new Teleporter();
                bank = game.spawn(teleporter);
                gameFrame.getBank().setText("€ " + bank);
                break;
        }
    }

    /**
     * Starts a new game
     * @param levelChoice the level that the user wants to play
     */
    public void startNewGame(String levelChoice) {
        synchronized (lock){
            System.out.println(levelChoice);
            game.setLevelName(levelChoice);
            game.setGameState(true);
        }
    }

    /**
     * Tell the game to restart the game
     */
    public void restartLevel(){
        game.restart();
    }

    /**
     * Set the game to paused, and show a dialog to the user
     */
    public void pauseGame(){
        game.setGameState(false);
        int option = JOptionPane.showOptionDialog(null, "Game is paused. Press OK to continue",
                "Pause", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, null, null);

        if (option <= 0)  {
            game.setGameState(true);
        }

    }

    /**
     * Quits the game
     */
    public void quitGame(){
        System.exit(0);
    }
}
