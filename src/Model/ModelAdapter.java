package Model;

import GUI.GameFrame;
import GUI.GameWindow;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ModelAdapter {

    // GUI canvas
    private GameFrame gui;

    // The image to be sent to GUI
    private BufferedImage image;


    public ModelAdapter(GameFrame gui) {
        this.gui = gui;
    }

    public void setBufferedImage(BufferedImage bi) {
        this.image = bi;
        gui.getGameWindow().setBufferedImage(bi);
        gui.getGameWindow().update();
        //System.out.println("updated window");
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
