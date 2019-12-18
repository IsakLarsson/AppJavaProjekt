package Model;

import GUI.GameFrame;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A adapter tha acts a middle hand between the model and the view
 */
public class ModelAdapter {

    // GUI canvas
    private GameFrame gui;

    // The image to be sent to GUI
    private BufferedImage image;

    //
    private ArrayList<String> levelsList;

    //
    private Queue<String> levels;

    /**
     * Constructor for the adapter object
     * @param gui a reference to the gui
     * @param levels a list of level names
     */
    public ModelAdapter(GameFrame gui, ArrayList<String> levels) {
        this.gui = gui;
        this.levelsList = levels;
        this.levels = new LinkedList<>(levelsList);
        this.levels.poll();
    }

    /**
     * Sends the image to the gui
     * @param bi the image
     */
    public void setBufferedImage(BufferedImage bi) {
        image = bi;
        gui.getGameWindow().setBufferedImage(bi);
        gui.getGameWindow().update();
    }

    /**
     * Tells the user that the level is won.
     * If it was the last level, tell that to user as well.
     * @param game a reference to the game object
     */
    public void levelWon(Game game) {
        String[] options = {"Next", "Restart"};
        int option = JOptionPane.showOptionDialog(null,
                "You won this level. Continue to the next one, or restart",
                "You won!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (option <= 0) {
            if (levels.peek() == null) {
                JOptionPane.showMessageDialog(gui.getFrame(), "Wow, did you just win the game? Cool.", "gz", JOptionPane.PLAIN_MESSAGE);
                game.nextLevel(levelsList.get(0));
            } else {
                game.nextLevel(levels.poll());
            }
        }
        else{
            game.nextLevel(game.getLevelName());
        }
    }

    /**
     * Tell the user he/she lost the game
     * @param game reference to the game object
     */
    public void timeIsOut (Game game) {
        JOptionPane.showMessageDialog(gui.getFrame(), "Time is out fking noob", "lol", JOptionPane.PLAIN_MESSAGE);
        game.nextLevel(game.getLevelName());
    }

    /**
     * Sets the money in the gui
     * @param money the money
     */
    public void setMoney(int money) {
        gui.getBank().setText("€ " + money);
    }

    /**
     * Sets the time in the gui
     * @param timeLimit the time
     */
    public void setTime(int timeLimit){
        gui.getTimer().setText(" Time:" + timeLimit);
    }

    /**
     * Gets the image
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

}
