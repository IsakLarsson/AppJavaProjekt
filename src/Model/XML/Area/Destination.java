package Model.XML.Area;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class that acts a next destination for a unit to move to
 */
public class Destination {
    private Queue<Integer> q;
    private TeleportArea teleArea;

    /**
     * Constructor, creates a new linked list
     */
    public Destination() {
        q = new LinkedList<>();
    }

    /**
     * Calculates a queue of pixel positions to the next tile
     * @param tiles a queue of tiles of the path
     * @return a queue with positions between two tiles
     */
    public Queue<Integer> calculateQueue(LinkedList<Tile> tiles) {

        if (tiles.isEmpty()) {
            return q;
        }

        Tile firstTile = tiles.pop();
        Tile secondTile = tiles.peek();
        String direction = "";
        int dest = 0;
        int start = 0;

        if (firstTile instanceof AreaEffect){
            teleArea = (TeleportArea) firstTile;
            teleArea.landOn(tiles);
        }

        if (secondTile == null) {
            return q;
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

        System.out.println("Returning: " + q);
        return q;
    }
}
