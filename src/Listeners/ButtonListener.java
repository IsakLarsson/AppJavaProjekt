package Listeners;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    //
    Controller controller;
    //
    public ButtonListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        System.out.println("Button pressed " + s);

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
