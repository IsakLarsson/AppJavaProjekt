package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Model extends SwingWorker {

    // Arraylist of frames
    private ArrayList<BufferedImage> frames;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    public Model(ArrayList<BufferedImage> al, ModelAdapter ma) {
        this.frames = al;
        this.adapter = ma;
    }

    @Override
    protected Object doInBackground() {


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
