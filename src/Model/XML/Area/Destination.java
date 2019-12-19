package Model.XML.Area;

import Model.Unit.Unit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * A class that acts a next destination for a unit to move to.
 *
 * @version 1.0 18 December 2019
 * @author Joel Bystedt <id17@cs.umu.se>
 */

public class Destination {
    private Queue<Integer> q;
    private TeleportInArea teleArea;

    /**
     * Constructor, creates a new linked list
     */
    public Destination() {
        q = new LinkedList<>();
    }

    /**
     * Calculates a queue of pixel positions to the next tile
     * @param unit a queue of tiles of the path
     * @return a queue with positions between two tiles
     */
    public Queue<Integer> calculateQueue(Unit unit) {
        LinkedList<Tile> tiles = unit.getPath();

        // Return empty queue if path is empty.
        if (tiles.isEmpty()) {
            return q;
        }

        Tile firstTile = tiles.pop();
        Tile secondTile = tiles.peek();
        String direction = "";
        int dest = 0;
        int start = 0;

        // if secondTile is null end is reached and return empty queue.
        if (secondTile == null) {
            return q;
        }

        //If tile is a teleportInArea teleport
        if (firstTile instanceof TeleportInArea){
            for(int i = 0; i < 4; i++){
                tiles.removeFirst();
            }
            unit.setTileQueue(tiles);
            teleArea = (TeleportInArea) firstTile;
            return teleArea.landOn(tiles);
        }


        if (firstTile.getxCoordinate() < secondTile.getxCoordinate()
                || firstTile.getxCoordinate() > secondTile.getxCoordinate()) {
            dest = secondTile.getxCoordinate()*firstTile.getSize();
            start = firstTile.getxCoordinate()*firstTile.getSize();
            direction = "horizontally";
        }
        else if (firstTile.getyCoordinate() < secondTile.getyCoordinate()
                || firstTile.getyCoordinate() > secondTile.getyCoordinate()) {
            dest = secondTile.getyCoordinate()*firstTile.getSize();
            start = firstTile.getyCoordinate()*firstTile.getSize();
            direction = "vertically";
        }

        while (start < dest) {

            if (direction.equals("horizontally")) {
                q.add(start);
                q.add(firstTile.getyCoordinate()*firstTile.getSize());
            }
            else {
                q.add(firstTile.getxCoordinate()*firstTile.getSize());
                q.add(start);
            }

            start++;
        }

        while (start > dest) {

            if (direction.equals("horizontally")) {
                q.add(start);
                q.add(firstTile.getyCoordinate()*firstTile.getSize());
            }
            else {
                q.add(firstTile.getxCoordinate()*firstTile.getSize());
                q.add(start);
            }

            start--;
        }

        return q;
    }
}
