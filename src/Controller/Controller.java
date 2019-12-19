package Controller;

import Model.Logic.Game;
import Model.Logic.Level;
import Adapter.ModelAdapter;
import View.GameFrame;
import View.GameWindow;
import Listeners.ButtonListener;
import Listeners.MenuListener;
import Model.Unit.Farmer;
import Model.Unit.Soldier;
import Model.Unit.Teleporter;
import Model.XML.XMLCreator;
import Model.XML.XMLParser;
import Model.XML.XMLSchemaValidator;

import javax.swing.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * A controller that controls the model and view
 *
 * @version 1.0 18 December 2019
 * @author Joel Bystedt <id17jbt@cs.umu.se>
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 * @author Isak Larsson <id17iln@cs.umu.se>
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class Controller {

    /**Gameframe*/
    private GameFrame gameFrame;

    // The window inside the frame
    private GameWindow gameWindow;

    // The listeners
    private MenuListener menuListener;
    private ButtonListener buttonListener;

    // Adapter between the model and view
    private ModelAdapter adapter;

    // Interval for which the game should update
    private int updateInterval = 30;

    // A game object
    private Game game;

    //
    private Object lock;
    private Object startObject;

    //
    private XMLParser parser;

    // Path to the xml
    private String filePath;

    /**
     * The controller creates two threads, one for the gui,
     * and one for the game logic
     * @param filePath is the path to the xml file
     */
    public Controller(String filePath){
        //TODO: Möjliggöra xml-fil som inparameter i terminal
        //TODO: Namnge .jar filen AntiTD.jar
        //Validerar bara Levels.XML i src.model.xml

        InputStream stream = null;
        if (filePath != null) {
            try {
                stream = new FileInputStream(new File(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            stream = ClassLoader.getSystemResourceAsStream("res/Levels.xml");
        }

        XMLSchemaValidator validator = new XMLSchemaValidator();
        int validateValue = validator.validateXML(new StreamSource(stream));
        if (validateValue < 0) {
            JOptionPane.showMessageDialog(null, "Format of XML-file is incorrect");
            System.exit(-1);
        }

        if (filePath != null) {
            try {
                stream = new FileInputStream(new File(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            stream = ClassLoader.getSystemResourceAsStream("res/Levels.xml");
        }

        lock = new Object();
        startObject = new Object();

        this.filePath = filePath;
        parser = new XMLParser(stream);

        Level level = parser.parseXML();
        if (level == null) {
            JOptionPane.showOptionDialog(null,
                    "There was something wrong with the XML parsing",
                    "Error!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, null,null);
            System.exit(0);
        }

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

            //Disable buttons
            gameFrame.setButtonState(false);

            // Disable tool bar
            gameWindow.setToolBarState(false);

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

                if (filePath != null) {
                    try {
                        stream = new FileInputStream(new File(filePath));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    stream = ClassLoader.getSystemResourceAsStream("res/Levels.xml");
                }
                game = new Game(adapter, updateInterval, stream, filePath);
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
        game.teleport();
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
                //gameFrame.getButton3().setEnabled(false);
                break;
        }
    }

    /**
     * Starts a new game
     */
    public void startNewGame() {
        synchronized (lock){
            String uName = JOptionPane.showInputDialog("Enter your name: ");
            game.setUserName(uName);
            game.setLevelName(parser.getLevels().get(0));
            game.setGameState(true);
            gameFrame.setButtonState(true);
            gameFrame.getNewGame().setText("Restart");
            gameWindow.setToolBarState(true);
        }
    }

    /**
     * Tell the game to restart the game
     */
    public void restartLevel(){
        gameFrame.getButton3().setEnabled(true);
        game.setGameState(true);
        game.setTeleported(true);
        game.nextLevel(game.getLevelName());
    }

    /**
     * Restarts the game.
     */
    public void restartGame() {
        String uName = JOptionPane.showInputDialog("Enter your name: ");
        gameFrame.getButton3().setEnabled(true);
        game.setUserName(uName);
        game.setGameState(true);
        game.setTeleported(true);
        adapter = new ModelAdapter(gameFrame, parser.getLevels());
        game.refreshAdapter(adapter);
        game.nextLevel(parser.getLevels().get(0));
    }

    /**
     * Set the game to paused, and show a dialog to the user.
     */
    public void pauseGame(){
        game.setGameState(false);
        gameFrame.setButtonState(false);
        gameFrame.getPause().setText("Resume");
        gameWindow.setToolBarState(false);
    }

    /**
     * Resumes game.
     */
    public void resumeGame() {
        game.setGameState(true);
        gameFrame.setButtonState(true);
        gameFrame.getPause().setText("Pause");
        gameWindow.setToolBarState(true);
    }

    /**
     * Quits the game.
     */
    public void quitGame(){
        System.exit(0);
    }
}
