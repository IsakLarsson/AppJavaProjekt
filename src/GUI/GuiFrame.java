package GUI;

import javax.swing.*;
import java.awt.*;

public class GuiFrame {
    private JFrame frame;

    public GuiFrame(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel upperPanel = buildUpperPanel();
        JPanel lowerPanel = buildlowerPanel();

        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(lowerPanel, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Helpmethod for showing the gui
     */
    public void show() {
        frame.setVisible(true);
    }

    /**
     * Builds the upper panel
     *
     * @return The created panel
     */
    private JPanel buildUpperPanel() {
        JPanel upperPanel = new JPanel();

        return upperPanel;
    }

    /**
     * Builds the lower panel
     *
     * @return The created panel
     */
    private JPanel buildlowerPanel() {
        JPanel lowerPanel = new JPanel();

        return lowerPanel;
    }
}
