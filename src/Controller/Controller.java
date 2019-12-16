package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import Listeners.ButtonListener;
import Listeners.MenuListener;
import Model.*;
import Model.Unit.Farmer;
import Model.Unit.Soldier;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller {

    // The gameFrame of the game
    private GameFrame gameFrame;
    GameWindow gameWindow;

    //
    private MenuListener menuListener;
    private ButtonListener buttonListener;

    //
    private ModelAdapter adapter;

    //
    private int updateInterval = 50;

    private int bank;
    //
    private Game game;

    //
    private Object lock;
    private Object startObject;

    //
    private Boolean newGame = false;

    public Controller(){
        lock = new Object();
        startObject = new Object();

        SwingUtilities.invokeLater(() -> {

            // An actionlistener for the spawn buttons
            buttonListener = new ButtonListener(this);
            menuListener = new MenuListener(this);

            // A panel that you move on
            // A window gameFrame containing a menubar
            gameWindow = new GameWindow();
            gameFrame = new GameFrame("Game", gameWindow, buttonListener,
                    menuListener);
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
                adapter = new ModelAdapter(gameWindow);
                game = new Game(adapter, updateInterval, this);
                game.start();
            } catch (InterruptedException e) {
                //Skriv lämpligt fel
            }
        }
    }

    public void startGame(Object lock) {


    }

    public void setMoney(int money){
        gameFrame.getLabel().setText("€ " + money);
    }


    public void openDialog(String title, String text) {
        JOptionPane.showMessageDialog(gameFrame.getFrame(),text,title,JOptionPane.PLAIN_MESSAGE);
    }

    public void teleportUnit() {

    }

    public void splitPath() {

    }

    public void spawnUnit (String s) {
        switch (s) {
            case "Farmer":
                Farmer farmer = new Farmer();
                bank = game.spawn(farmer, farmer.getCost());
                gameFrame.getLabel().setText("€ " + bank);
                break;
            case "Soldier":
                Soldier soldier = new Soldier();
                bank = game.spawn(soldier, soldier.getCost());
                gameFrame.getLabel().setText("€ " + bank);
                break;
            case "Teleporter":
                break;
        }
    }

    public void startNewGame() {
        game.setNewGame(true);
    }
}
