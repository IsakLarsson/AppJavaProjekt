package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Model extends SwingWorker {

    // Arraylist of frames
    private ArrayList<BufferedImage> frames;

    // Boolean that stops the game if needed
    private Boolean gameIsRunning;

    public Model(ArrayList<BufferedImage> al) {
        frames = al;
    }

    @Override
    protected Object doInBackground() {

        // While game is running
        while (gameIsRunning) {
            
        }

        publish();
        return null;
    }

    @Override
    protected void process(List chunks) {
        for (Object o : chunks) {
           frames.add((BufferedImage) o);
        }
    }

    public void addStuff() {

    }

    public void stopGame() {}

}
