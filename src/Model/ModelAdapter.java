package Model;

import GUI.GameFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ModelAdapter {

    // GUI canvas
    private GameFrame gui;

    // The image to be sent to GUI
    private BufferedImage image;

    //
    private Queue<String> levels;

    public ModelAdapter(GameFrame gui, ArrayList<String> levels) {
        this.gui = gui;
        this.levels = new LinkedList<>(levels);
        this.levels.poll();
    }

    public void setBufferedImage(BufferedImage bi) {
        image = bi;
        gui.getGameWindow().setBufferedImage(bi);
        gui.getGameWindow().update();
    }

    public void levelWon(Game game) {
        String[] options = {"Next", "Restart"};
        int option = JOptionPane.showOptionDialog(null,
                "You won this level. Continue to the next one, or restart",
                "You won!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (option <= 0) {
            if (levels.peek() == null) {
                JOptionPane.showMessageDialog(gui.getFrame(), "Wow, did you just win the game? Cool.", "gz", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            } else {
                game.nextLevel(levels.poll());
            }
        }
        else{
            game.nextLevel(game.getLevelName());
        }
    }

    public void timeIsOut (Game game) {
        JOptionPane.showMessageDialog(gui.getFrame(), "Time is out fking noob", "lol", JOptionPane.PLAIN_MESSAGE);
        game.nextLevel(game.getLevelName());
    }

    public void setMoney(int money) {
        gui.getBank().setText("€ " + money);
    }

    public void setTime(int timeLimit){
        gui.getTimer().setText(" Time:" + timeLimit);
    }

    public BufferedImage getImage() {
        return image;
    }

}
