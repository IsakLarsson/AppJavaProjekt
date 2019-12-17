package Listeners;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener for the button objects
 */
public class ButtonListener implements ActionListener {

    // Controller
    private Controller controller;

    /**
     * Constructor for the listener
     * @param controller
     */
    public ButtonListener(Controller controller) {
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
            case "Teleport":
                controller.teleportUnit();
                break;
            case "Switch Path":
                controller.splitPath();
                break;
            default:
                controller.spawnUnit(s);
                break;
        }
    }
}
