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

public class Controller {

    // The gameFrame of the game
    private GameFrame gameFrame;
    private GameWindow gameWindow;

    //
    private MenuListener menuListener;
    private ButtonListener buttonListener;

    //
    private ModelAdapter adapter;

    //
    private int updateInterval = 50;

    //
    private Game game;

    //
    private Object lock;
    private Object startObject;

    //
    private Boolean newGame = false;

    //
    String filePath;

    public Controller(String filePath){
        lock = new Object();
        startObject = new Object();

        this.filePath = filePath;
        XMLParser parser = new XMLParser(filePath);
        parser.parseXML();

        SwingUtilities.invokeLater(() -> {

            // An actionlistener for the spawn buttons
            buttonListener = new ButtonListener(this);
            menuListener = new MenuListener(this);

            // A panel that you move on
            // A window gameFrame containing a menubar
            gameWindow = new GameWindow();
            gameFrame = new GameFrame("Game", gameWindow, buttonListener,
                    menuListener, parser.getNumberOfLevels());
            gameFrame.setupListeners(buttonListener);

            // Show the gui
            gameFrame.show();
            synchronized (lock){
                lock.notify();
            }
        });

        synchronized (lock){
            try {
                lock.wait();
                adapter = new ModelAdapter(gameFrame);
                game = new Game(adapter, updateInterval, filePath);
                game.start();
            } catch (InterruptedException e) {
                //Skriv lämpligt fel
            }
        }
    }


    public void openDialog(String title, String text) {
        JOptionPane.showMessageDialog(gameFrame.getFrame(),text,title,JOptionPane.PLAIN_MESSAGE);
    }

    public void teleportUnit() {

    }

    public void splitPath() {
        game.changePath();
    }

    public void spawnUnit (String s) {
        int bank;
        switch (s) {
            case "Farmer":
                Farmer farmer = new Farmer();
                bank = game.spawn(farmer, farmer.getCost());
                gameFrame.getBank().setText("€ " + bank);
                break;
            case "Soldier":
                Soldier soldier = new Soldier();
                bank = game.spawn(soldier, soldier.getCost());
                gameFrame.getBank().setText("€ " + bank);
                break;
            case "Teleporter":
                Teleporter teleporter = new Teleporter();
                bank = game.spawn(teleporter, teleporter.getCost());
                gameFrame.getBank().setText("€ " + bank);
                break;
        }
    }

    public void startNewGame(String levelChoice) {

        synchronized (lock){
            game.setLevelName(levelChoice);
            game.setGameState(true);
        }
    }

    public void restartLevel(){

    }

    public void pauseGame(){
        game.setGameState(false);
        int option = JOptionPane.showOptionDialog(null, "Game is paused. Press OK to continue",
                "Pause", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, null, null);

        if (option <= 0)  {
            game.setGameState(true);
        }

    }

    public void quitGame(){
        System.exit(0);
    }

}
