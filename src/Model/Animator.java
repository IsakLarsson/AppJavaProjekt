package Model;

import Model.Unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Animator {
    private List<Unit> unitList;
    private Timer t;
    private Object lock;

    public Animator(List unitList) {
        this.unitList = unitList;
        lock = new Object();

    }

    public void run() {
        synchronized (lock) {
            for (Unit unit : unitList) {
                unit.move();
            }
        }
    }

    //Safe to call from any thread
    public void addUnit(Unit unit) {
        synchronized(lock) {
            unitList.add(unit);
        }
    }


}
