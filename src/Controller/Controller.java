package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import Listeners.ButtonListener;
import Listeners.MenuListener;
import Model.*;


import javax.swing.*;

public class Controller {

    // The gameFrame of the game
    private GameFrame gameFrame;
    GameWindow gameWindow;

    //
    private MenuListener menuListener;
    private ModelAdapter adapter;

    //
    private ButtonListener buttonListener;
    private int updateInterval = 1000;


    public Controller(){
        Object lock = new Object();

        SwingUtilities.invokeLater(() -> {
            //
            menuListener = new MenuListener();

            // An actionlistener for the spawn buttons
            buttonListener = new ButtonListener();

            // A panel that you draw on
            // A window gameFrame containing a menubar
            gameWindow = new GameWindow();
            gameFrame = new GameFrame("Game", gameWindow, buttonListener,
                    menuListener);
            gameFrame.setupListeners(buttonListener);
            // Show the gui
            gameFrame.show();

            // An adapter that acts a middlehand between the gui and the model
            //adapter = new ModelAdapter(gameWindow);
            // A model
            //Worker worker = new Worker(adapter);
            //worker.execute();
            synchronized (lock){
                lock.notify();
            }

        });

        synchronized (lock){
            try {
                lock.wait();
                adapter = new ModelAdapter(gameWindow);
                //TODO synka denna del med invokelater, annars hinner guit inte
                // initialiseras innan game.run körs ----> nullpointer
                Game game = new Game(adapter, updateInterval);
                game.start();
            } catch (InterruptedException e) {
                //Skriv lämpligt fel
            }
        }

    }


    public void openDialog(String title, String text) {
        JOptionPane.showMessageDialog(gameFrame.getFrame(),text,title,JOptionPane.PLAIN_MESSAGE);
    }

    public void spawnUnit (String s) {
        
    }
}
