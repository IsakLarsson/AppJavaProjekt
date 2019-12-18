package Model;

import Model.Unit.Unit;
import Model.XML.Area.Destination;
import Model.XML.Area.Tile;

import java.util.*;

/**
 * Animator that handles the calculations, positions, and units.
 */
public class Animator {
    private List<Unit> unitList;
    private Object lock;

    /**
     * Constructor for the animator object
     * @param unitList
     */
    public Animator(List unitList) {
        this.unitList = unitList;
        lock = new Object();

    }

    /**
     * Gets a queue between two tiles and sends it to the unit
     * @param destination Destination object
     * @param unit the unit that uses the queue
     */
    public void calculatePositionQueue(Destination destination, Unit unit) {
        synchronized (lock) {
            Queue<Integer> queue = destination.calculateQueue(unit.getPath());
            unit.setPixelPositionQueue(queue);
        }
    }

    /**
     * Adds a unit to a unit list and sets it tile list
     * @param unit the unit
     * @param tiles the path
     */
    public void addUnit(Unit unit, LinkedList<Tile> tiles) {
        synchronized(lock) {
            unit.setTileQueue(tiles);
            unitList.add(unit);
        }
    }
}
