package Model;

import View.GameFrame;
import Model.XML.SQLHandler;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A adapter tha acts a middle hand between the model and the view
 */
public class ModelAdapter {

    // View canvas
    private GameFrame gui;

    // The image to be sent to View
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
     *
     */
    public void refreshLevelQueue() {
        levels = new LinkedList<>(levelsList);
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
        if (levels.peek() == null) {
            String[] options = {"Play again", "Quit", "High score"};
            int option = JOptionPane.showOptionDialog(null,
                    "Wow, you beaned the game!",
                    "Gz!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option <= 0) {
                refreshLevelQueue();
                game.nextLevel(levels.poll());
            } else if (option == 1) {
                System.exit(0);
            } else {
                game.displayHighscore();
            }
        } else {
            String[] options = {"Next", "Restart", "High score"};
            int option = JOptionPane.showOptionDialog(null,
                    "You won this level. Continue to the next one, or restart",
                    "You won!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (option <= 0) {
                game.nextLevel(levels.poll());
            } else if (option == 1) {
                game.nextLevel(game.getLevelName());
            } else {
                game.displayHighscore();
            }

        }
    }

    /**
     * Prints high score in a dialog window
     */
    public void printHighScore() {
        SQLHandler sql = new SQLHandler();
        ArrayList<HighScore> score = sql.selectTable();
        StringBuilder strB = new StringBuilder();
        strB.append(" ------- Top 5 Highscores -------\n");
        for (int i = 0; i < 5; i++) {
            strB.append(score.get(i).getName()).append(" - ");
            strB.append(score.get(i).getLevel()).append(" - ");
            strB.append(score.get(i).getScore()).append("\n");
        }
        JOptionPane.showMessageDialog(gui.getFrame(), strB, "High score",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Tell the user he/she lost the game
     * @param game reference to the game object
     */
    public void timeIsOut (Game game) {
        JOptionPane.showMessageDialog(gui.getFrame(), "You died",
                "lol", JOptionPane.PLAIN_MESSAGE);
        game.nextLevel(game.getLevelName());
    }

    public void errorMessage(String errorMessage){
        JOptionPane.showMessageDialog(gui.getFrame(), errorMessage,
                "ERROR", JOptionPane.PLAIN_MESSAGE);
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
