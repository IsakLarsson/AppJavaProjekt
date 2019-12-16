package Model;

import GUI.GameWindow;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ModelAdapter {

    // GUI canvas
    private GameWindow gui;

    // The image to be sent to GUI
    private BufferedImage image;

    public ModelAdapter(GameWindow gui) {
        this.gui = gui;
    }

    public void setBufferedImage(BufferedImage bi) {
        this.image = bi;
        gui.setBufferedImage(bi);
        gui.update();
    }

    public BufferedImage getImage() {
        return image;
    }
}
