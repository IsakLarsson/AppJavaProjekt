package Model.Logic;

import Model.Unit.Teleporter;
import Model.Unit.Unit;
import Model.XML.Area.Destination;
import Model.XML.Area.Tile;

import java.util.*;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * Animator that handles the calculations, positions, and units.
 *
 * @version 1.0 18 December 2019
 * @author Axel Jakobsson, c18ajn@cs.umu.se
 * @author Isak Larsson, id17@ian@cs.umu.se
 * @author Joel Bystedt, id17jbt@cs.umu.se
 * @author Albin Jönsson, c18ajs@cs.umu.se
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
            Queue<Integer> queue = destination.calculateQueue(unit);
            unit.setPixelPositionQueue(queue);
            if(unit instanceof Teleporter)
                ((Teleporter) unit).increaseSteps();
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
