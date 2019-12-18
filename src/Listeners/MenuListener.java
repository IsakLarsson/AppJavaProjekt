package Listeners;

import Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener for the menu objects
 */
public class MenuListener implements ActionListener {

    // Controller
    private Controller controller;

    /**
     * Constructor for the listener
     * @param controller
     */
    public MenuListener(Controller controller){
        this.controller = controller;
    }

    /**
     * Override the method to do something when pressed
     * @param e the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "New Game":
                controller.startNewGame();
                break;
            case "Restart":
                controller.restartGame();
                break;
            case "Restart level":
                controller.restartLevel();
                break;
            case "Pause":
                controller.pauseGame();
                break;
            case "Resume":
                controller.resumeGame();
                break;
            case "Quit":
                controller.quitGame();
                break;
            case "Help":
                controller.openDialog(s, "How to play the game");
                break;
            case "About":
                controller.openDialog(s, "About the game");
                break;
        }
    }
}
