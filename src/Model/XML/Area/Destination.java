package Model.XML.Area;

import java.util.LinkedList;
import java.util.Queue;

public class Destination {
    private Queue<Integer> q;
    public Destination() {
        q = new LinkedList<>();
    }

    public Queue calculateQueue(LinkedList<Tile> tiles) {
        Tile firstTile = tiles.poll();
        Tile secondTile = tiles.peek();
        String direction = "";
        int dest = 0;
        int start = 0;
        int i = 0;

        if (firstTile.getxCoordinate() < secondTile.getxCoordinate()) {
            dest = secondTile.getxCoordinate();
            start = firstTile.getxCoordinate();
            direction = "horizontally";
        }
        else if (firstTile.getyCoordinate() < secondTile.getyCoordinate()) {
            dest = secondTile.getyCoordinate();
            start = firstTile.getyCoordinate();
            direction = "vertically";
        }

        while (start < dest) {

            if (direction.equals("horizontally")) {
                q.add(start + i);
                q.add(firstTile.getyCoordinate());
            }
            else {
                q.add(firstTile.getyCoordinate());
                q.add(start + i);
            }

            start++;
            i++;
        }

        return q;
    }
}
