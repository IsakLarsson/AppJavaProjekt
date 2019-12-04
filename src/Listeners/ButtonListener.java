package Listeners;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    //
    private Controller controller;

    public ButtonListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button pressed");
        String s = e.getActionCommand();
        System.out.println(s);

        switch (s) {
            case "Farmer":
                controller.openDialog(s, "This is a farmer");
                break;
            case "Soldier":
                break;
            case "Teleporter":
                break;
        }

    }
}
