package Controller;

import GUI.GameFrame;
import GUI.GameWindow;
import Listeners.ButtonListener;
import Model.*;


import javax.swing.*;

public class Controller {

    // The gameFrame of the game
    private GameFrame gameFrame;

    //
    private ButtonListener buttonListener;

    public Controller(){

        //
        SwingUtilities.invokeLater(() -> {

            // An actionlistener for the spawn buttons
            this.buttonListener = new ButtonListener(this);

            // A panel that you draw on
            GameWindow gameWindow = new GameWindow();

            // A window gameFrame containing a menubar
            gameFrame = new GameFrame("Game", gameWindow, this.buttonListener);

            // Show the gui
            gameFrame.show();

            // An adapter that acts a middlehand between the gui and the model
            ModelAdapter adapter = new ModelAdapter(gameWindow);

            // A model
            Model model = new Model(adapter);

            // Update the gui with 1 seconds intervals
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // Update the gui
            gameWindow.update();

        });
    }

    public void openDialog(String title, String text) {
        JOptionPane.showMessageDialog(gameFrame.getFrame(),title,text,JOptionPane.PLAIN_MESSAGE);
    }
}
