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

    //
    private ButtonListener buttonListener;



    public Controller(){

        //TODO Nya tråder UTANFÖR invoke later

        SwingUtilities.invokeLater(() -> {
            //
            this.menuListener = new MenuListener(this);

            // An actionlistener for the spawn buttons
            this.buttonListener = new ButtonListener(this);

            // A panel that you draw on
            // A window gameFrame containing a menubar
            gameWindow = new GameWindow();
            gameFrame = new GameFrame("Game", gameWindow, buttonListener,
                    menuListener);
            gameFrame.setupListeners(buttonListener);
            // Show the gui
            gameFrame.show();

            // An adapter that acts a middlehand between the gui and the model
            //ModelAdapter adapter = new ModelAdapter(gameWindow);
            // A model
            //Worker worker = new Worker(adapter);
            //worker.execute();


        });

        Game game = new Game(gameWindow);
        game.run();
    }


    public void openDialog(String title, String text) {
        JOptionPane.showMessageDialog(gameFrame.getFrame(),text,title,JOptionPane.PLAIN_MESSAGE);
    }

    public void spawnUnit (String s) {
        
    }
}
