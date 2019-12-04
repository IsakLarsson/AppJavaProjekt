package Listeners;

import Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {

    //
    private Controller controller;

    public MenuListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Menu item pressed");
        String s = e.getActionCommand();
        System.out.println(s);

        switch (s) {
            case "Help":
                controller.openDialog(s, "How to play the game");
                break;
            case "About":
                break;
            }

    }
}
