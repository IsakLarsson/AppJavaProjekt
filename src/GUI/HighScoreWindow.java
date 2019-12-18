package GUI;

import Model.XML.SQLHandler;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * The high score window of the game. Shows, at most, top 30 scores.
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class HighScoreWindow {
    /** Text area where high score will be printed **/
    private JTextArea textArea;
    /** SQl handler **/
    private SQLHandler sql;

    //För testning
    public static void main(String[] args) {
        HighScoreWindow hi = new HighScoreWindow();
    }

    /**
     * Creates a high score window
     */
    public HighScoreWindow() {
        sql = new SQLHandler();
        JFrame frame = new JFrame("High score");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,600));
        frame.setLayout(new BorderLayout());

        JLabel highScore = new JLabel();
        highScore.setText("High score");
        highScore.setFont(new Font("Monospaced", Font.PLAIN, 32));
        highScore.setHorizontalAlignment(SwingConstants.CENTER);

        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(200,200));
        getCurrentHighScores();

        frame.add(highScore, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        //Center the window
        frame.setLocationRelativeTo(null);
    }

    /**
     * Appends all current high scores to textArea in HighScoreWindow
     */
    public void getCurrentHighScores() {
        sql = new SQLHandler();
        ArrayList<HighScore> score = sql.selectTable();
        int tableSize = sql.getTableSize();
        if (tableSize > 30) {
            tableSize = 30;
            for (int i = 0; i < tableSize; i++) {
                textArea.append("Name: " + score.get(i).getName() + "\t");
                textArea.append("Level: " + score.get(i).getLevel() + "\t");
                textArea.append("Score: " + score.get(i).getScore() + "\n");
            }
        }
        else {
            for (int i = 0; i < tableSize; i++) {
                textArea.append("Name: " + score.get(i).getName() + "\t");
                textArea.append("Level: " + score.get(i).getLevel() + "\t");
                textArea.append("Score: " + score.get(i).getScore() + "\n");
            }
        }
    }
}
