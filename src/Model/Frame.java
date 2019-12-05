package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Frame {

    // size constants

    // image
    private BufferedImage image;

    // constructor
    public Frame(BufferedImage bi) {
        this.image = bi;
    }

    public Frame() {}

    public BufferedImage copyImage (BufferedImage bi) {
        Frame frame = new Frame(bi);
        return frame.getImage();
    }

    public BufferedImage createImage () {
        return new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage getImage() {
        return image;
    }
}
