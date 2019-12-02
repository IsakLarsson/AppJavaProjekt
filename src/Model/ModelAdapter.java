package Model;

import GUI.GameWindow;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ModelAdapter {

    // GUI canvas
    private GameWindow gui;

    public ModelAdapter(GameWindow gui) {
        this.gui = gui;
    }

    public void setBufferedImage(BufferedImage bi) {
        gui.setBufferedImage(bi);
    }
}
