package Model;

import Model.Unit.Unit;
import Model.XML.Area.Destination;
import Model.XML.Area.Tile;

import java.util.*;

public class Animator {
    private List<Unit> unitList;
    private Timer t;
    private Object lock;
    private Queue<Integer> queue;

    public Animator(List unitList) {
        this.unitList = unitList;
        lock = new Object();

    }

    public void run(Destination destination) {
        synchronized (lock) {
            for (Unit unit : unitList) {
                queue = destination.calculateQueue(unit.getPath());
                unit.move(queue);
            }
        }
    }

    //Safe to call from any thread
    public void addUnit(Unit unit, LinkedList<Tile> tiles) {
        synchronized(lock) {
            unit.setTileQueue(tiles);
            unitList.add(unit);
        }
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

}
